package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookentity.type.*;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.OrderService;
import com.homecook.homecookstorefront.service.TappayPaymentService;
import com.homecook.tappay.TapPayClient;
import com.homecook.tappay.api.DirectPayApi;
import com.homecook.tappay.api.RecordApi;
import com.homecook.tappay.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service(value = "tappayPaymentService")
public class DefaultTappayPaymentService implements TappayPaymentService
{
    private static final Logger log = LoggerFactory.getLogger(DefaultTappayPaymentService.class);

    @Value("${tappay.partner.key}")
    private String tapPayPartnerKey;
    @Value("${tappay.merchant.bank}")
    private String tapPayMerchantBankId;
    @Value("${website.domain}")
    private String websiteDomain;
    @Value("${website.backend.domain}")
    private String websiteBackendDomain;

    private OrderService orderService;
    private ModelService modelService;
    private TapPayClient tapPayClient;


    public DefaultTappayPaymentService(OrderService orderService,
                                       ModelService modelService,
                                       TapPayClient tapPayClient)
    {
        this.orderService = orderService;
        this.modelService = modelService;
        this.tapPayClient = tapPayClient;
    }

    public String getPaymentUrlForCreditCard(String prime, OrderEntity order)
    {
        PayByPrimeRequest payByPrimeRequest = new PayByPrimeRequest.Builder()
                .prime(prime)
                .partnerKey(tapPayPartnerKey)
                .merchantId(tapPayMerchantBankId)
                .amount(order.getTotalPrice().intValue())
                .orderNumber(order.getCode())
                .details(getPurchasedProductName(order))
                .cardholder(getCardholder(order.getCustomer()))
                .threeDomainSecure(true)
                .resultUrl(getResultUrl(order.getCode()))
                .build();
        log.info(payByPrimeRequest.toString());

        PayByPrimeResponse response;
        try
        {
            final DirectPayApi directPayApi = tapPayClient.getDirectPayApi();


            response = directPayApi.payByPrime(payByPrimeRequest);

            log.info(response.toString());

            if (response.getStatus() == 0)
            {
                return response.getPaymentUrl();
            }
        }
        catch (Exception e)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.PAYMENT_GATEWAY_CONNECTION_ERROR, "Could not connect to tappay payment server ");
        }

        if (response.getStatus() != 0)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.PSP_API_ERROR, response.getMessage());
        }

        return "";
    }

    @Transactional
    public PaymentTransactionEntity authorizePayment(String gatewayTransactionId)
    {
        final RecordResponse recordResponse = getRecord(gatewayTransactionId);
        final TradeRecord tradeRecord = recordResponse.getNumberOfTransactions() == 1 ? recordResponse.getTradeRecords().get(0) : null;

        if (tradeRecord != null)
        {
            final OrderEntity order = getOrderService().getOrderForCodeNoAuth(tradeRecord.getOrderNumber());

            final RecordStatus recordStatus = RecordStatus.fromValue(tradeRecord.getRecordStatus());
            if (RecordStatus.ERROR.equals(recordStatus))
            {
                PaymentTransactionEntity transaction = new PaymentTransactionEntity();
                transaction.setGatewayTransactionId(tradeRecord.getRecTradeId());
                transaction.setAmount(tradeRecord.getAmount());
                transaction.setCurrency(tradeRecord.getCurrency());
                transaction.setBankTransactionId(tradeRecord.getBankTransactionId());
                transaction.setTransactionType(PaymentTransactionType.VOID);
                transaction.setTransactionDate(new Date(tradeRecord.getTime()));
                transaction.setGatewayMessage(recordResponse.getMessage());
                transaction.setTransactionStatus(PaymentTransactionStatus.ERROR);
                transaction.setOrder(order);
                order.setOrderStatus(OrderStatusType.PROCESS_ERROR);

                getModelService().save(transaction);
                getModelService().save(order);

                return transaction;
            }


            PaymentTransactionType transactionType = null;

            if (RecordStatus.AUTH.equals(recordStatus))
            {
                transactionType = PaymentTransactionType.AUTHORIZATION;
            }
            else if (RecordStatus.OK.equals(recordStatus))
            {
                transactionType = PaymentTransactionType.CAPTURE;
            }

            PaymentTransactionEntity transaction = new PaymentTransactionEntity();
            transaction.setGatewayTransactionId(tradeRecord.getRecTradeId());
            transaction.setAmount(tradeRecord.getAmount());
            transaction.setCurrency(tradeRecord.getCurrency());
            transaction.setBankTransactionId(tradeRecord.getBankTransactionId());
            transaction.setTransactionType(transactionType);
            transaction.setTransactionDate(new Date(tradeRecord.getTime()));
            transaction.setGatewayMessage(recordResponse.getMessage());
            transaction.setTransactionStatus(PaymentTransactionStatus.SUCCESS);
            transaction.setOrder(order);

            final CardInfo cardInfo = tradeRecord.getCardInfo();
            if (cardInfo != null)
            {
                CreditCardPaymentInfoEntity creditCardPaymentInfo = new CreditCardPaymentInfoEntity();
                creditCardPaymentInfo.setCardBin(cardInfo.getBinCode());
                creditCardPaymentInfo.setCardLastFour(cardInfo.getLastFour());
                creditCardPaymentInfo.setCardType(getCreditCardType(cardInfo.getType()));
                getModelService().save(creditCardPaymentInfo);
                transaction.setCreditCardPaymentInfo(creditCardPaymentInfo);
            }
            getModelService().save(transaction);

            order.setPaymentStatus(PaymentStatusType.PAID);
            order.setPayTime(new Date(tradeRecord.getTime()));
            order.setOrderStatus(OrderStatusType.READY_TO_SHIP);
            getModelService().save(order);

            return transaction;
        }

        return null;
    }

    @Override
    public void validatePaymentIsValid(OrderEntity order)
    {
        if (PaymentStatusType.PAID.equals(order.getPaymentStatus()))
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ORDER_ALREADY_PAID, "Order code: [" + order.getCode() + "] already be paid.");
        }
    }


    private RecordResponse getRecord(String gatewayTransactionId)
    {
        RecordFilters recordFilters = new RecordFilters.Builder()
                .recTradeId(gatewayTransactionId)
                .build();
        try
        {
            final RecordApi recordApi = tapPayClient.getRecordApi();
            RecordRequest recordRequest = new RecordRequest.Builder()
                    .partnerKey(tapPayClient.getApiKey())
                    .filters(recordFilters)
                    .build();
            return recordApi.getRecord(recordRequest);
        }
        catch (Exception e)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.PAYMENT_GATEWAY_CONNECTION_ERROR, "Could not connect to tappay payment server ");
        }
    }

    private CreditCardType getCreditCardType(Integer cardType)
    {
        switch (cardType)
        {
            case 1:
                return CreditCardType.VISA;
            case 2:
                return CreditCardType.MASTER_CARD;
            case 3:
                return CreditCardType.JCB;
            case 4:
                return CreditCardType.UNION_PAY;
            case 5:
                return CreditCardType.AMEX;
            default:
                return null;
        }
    }


    private String getPurchasedProductName(OrderEntity order)
    {
        StringBuilder builder = new StringBuilder();

        for (OrderLineItemEntity lineItem : order.getLineItems())
        {
            builder.append(lineItem.getName());

            if (order.getLineItems().size() > 1)
            {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    private Cardholder getCardholder(CustomerEntity customer)
    {
        final AddressEntity defaultAddress = customer.getDefaultAddress();
        String zipCode = defaultAddress.getZipcode();
        String fullAddress = defaultAddress.getAddress();

        // 持卡人或購買人會員編號。用於 TapPay 詐欺檢測、會員資料管理時使用。
        String memberId = customer.getAccount();

        return new Cardholder.Builder()
                .phoneNumber(customer.getPhone())
                .name(customer.getName())
                .email(customer.getEmail())
                .zipCode(zipCode)
                .address(fullAddress)
                .nationalId("")
                .memberId(memberId)
                .build();
    }

    private ResultUrl getResultUrl(String orderCode)
    {
        return new ResultUrl.Builder()
                .frontendUrl(String.format(websiteDomain + "/orders/%s", orderCode))
                .backendNotifyUrl(websiteBackendDomain + "/api/v1/order/payment/result")
                .goBackUrl(websiteDomain)
                .build();
    }

    public OrderService getOrderService()
    {
        return orderService;
    }

    public void setOrderService(OrderService orderService)
    {
        this.orderService = orderService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
