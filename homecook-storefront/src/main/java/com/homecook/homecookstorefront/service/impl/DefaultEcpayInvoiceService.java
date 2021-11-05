package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.entity.OrderInvoiceEntity;
import com.homecook.homecookentity.entity.OrderLineItemEntity;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.InvoiceService;
import ecpay.invoice.integration.EcpayInvoiceClient;
import ecpay.invoice.integration.domain.CheckLoveCodeObj;
import ecpay.invoice.integration.domain.CheckLoveCodeResultObj;
import ecpay.invoice.integration.domain.IssueObj;
import ecpay.invoice.integration.domain.IssueResultObj;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ecpayInvoiceService")
public class DefaultEcpayInvoiceService implements InvoiceService
{
    private static final Logger log = LoggerFactory.getLogger(DefaultEcpayInvoiceService.class);
    private EcpayInvoiceClient invoiceClient;

    @Autowired
    public DefaultEcpayInvoiceService(EcpayInvoiceClient invoiceClient)
    {
        this.invoiceClient = invoiceClient;
    }

    @Override
    public IssueResultObj postIssue(final OrderEntity order)
    {
        final CustomerEntity customer = order.getCustomer();
        final OrderInvoiceEntity invoice = order.getInvoice();

        String customerAddress = "";
        if (customer.getDefaultAddress() != null && StringUtils.isNotEmpty(customer.getDefaultAddress().getAddress()))
        {
            customerAddress = customer.getDefaultAddress().getAddress();
        }

        IssueObj obj = new IssueObj();
        obj.setRelateNumber(order.getCode().substring(0, 30));
        obj.setCustomerAddr(customerAddress);
        obj.setCustomerPhone(StringUtils.defaultIfEmpty(customer.getPhone(), ""));
        obj.setCustomerEmail(StringUtils.defaultIfEmpty(invoice.getContactEmail(), ""));
        obj.setPrint("0");
        obj.setTaxType("1");
        obj.setSalesAmount(String.valueOf(order.getTotalPrice().intValue()));
        setInvoiceItem(obj, order);
        obj.setInvType("07");
        obj.setVat("1");

        switch (invoice.getInvoiceType())
        {
            case PERSON:
                obj.setCustomerName(customer.getName());
                obj.setCarruerType("1");
                obj.setCarruerNum("");
                break;
            case COMPANY:
                obj.setCustomerName(invoice.getInvoiceTitle());
                obj.setCustomerIdentifier(invoice.getBusinessNumber());
                obj.setPrint("1");
                obj.setDonation("0");
                obj.setCarruerType("");
                obj.setCarruerNum("");
                break;
            case DONATION:
                obj.setPrint("0");
                obj.setDonation("1");
                obj.setLoveCode(StringUtils.defaultIfEmpty(invoice.getLoveCode(), "168001"));
                obj.setCustomerName(customer.getName());
                break;
        }

        log.info("IssueObj: {}", obj);
        return invoiceClient.issue(obj);
    }


    private void setInvoiceItem(IssueObj obj, OrderEntity order)
    {
        StringBuilder itemNameBuilder = new StringBuilder();
        StringBuilder itemCountBuilder = new StringBuilder();
        StringBuilder itemPriceBuilder = new StringBuilder();
        StringBuilder itemWordBuilder = new StringBuilder();
        StringBuilder itemAmountBuilder = new StringBuilder();

        int size = order.getLineItems().size();
        for (int i = 0; i < size; i++)
        {
            final OrderLineItemEntity orderLineItem = order.getLineItems().get(i);

            itemNameBuilder.append(orderLineItem.getName());
            itemCountBuilder.append(orderLineItem.getQuantity());
            itemPriceBuilder.append(orderLineItem.getPrice().intValue());
            itemWordBuilder.append(orderLineItem.getProduct().getSalesUnit());
            itemAmountBuilder.append(orderLineItem.getSubtotal().intValue());

            if (size > 1 && i != size - 1)
            {
                itemNameBuilder.append("|");
                itemCountBuilder.append("|");
                itemPriceBuilder.append("|");
                itemWordBuilder.append("|");
                itemAmountBuilder.append("|");
            }
        }

        if (order.getTotalDiscounts() > 0)
        {
            itemNameBuilder.append("|").append("折扣金額");
            itemCountBuilder.append("|").append("1");
            itemPriceBuilder.append("|-").append(order.getTotalDiscounts().intValue());
            itemWordBuilder.append("|").append("筆");
            itemAmountBuilder.append("|-").append(order.getTotalDiscounts().intValue());
        }

        if (order.getShippingCost() > 0)
        {
            itemNameBuilder.append("|").append("運費");
            itemCountBuilder.append("|").append("1");
            itemPriceBuilder.append("|").append(order.getShippingCost().intValue());
            itemWordBuilder.append("|").append("筆");
            itemAmountBuilder.append("|").append(order.getShippingCost().intValue());
        }

        obj.setItemName(itemNameBuilder.toString());
        obj.setItemCount(itemCountBuilder.toString());
        obj.setItemPrice(itemPriceBuilder.toString());
        obj.setItemWord(itemWordBuilder.toString());
        obj.setItemAmount(itemAmountBuilder.toString());
    }

    @Override
    public boolean checkLoveCode(String loveCode)
    {
        CheckLoveCodeObj obj = new CheckLoveCodeObj();
        obj.setLoveCode(loveCode);

        CheckLoveCodeResultObj resultObj = invoiceClient.checkLoveCode(obj);

        if (resultObj == null) return false;

        if (10000010 == resultObj.getRtnCode())
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ECPAY_INVOICE_CHECK_LOVE_CODE_ERROR, "The system of the Ministry of Finance is currently under maintenance and cannot be verified. Please try again later");
        }


        return resultObj.getRtnCode() == 1 && "Y".equals(resultObj.getIsExist());
    }

}
