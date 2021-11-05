package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.entity.OrderLineItemEntity;
import com.homecook.homecookentity.entity.PaymentTransactionEntity;
import com.homecook.homecookstorefront.facade.PaymentFacade;
import com.homecook.homecookstorefront.service.*;
import com.homecook.tappay.models.BackendNotifyRequest;
import ecpay.invoice.integration.domain.IssueResultObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "paymentFacade")
public class DefaultPaymentFacade implements PaymentFacade
{
    private static final Logger log = LoggerFactory.getLogger(DefaultPaymentFacade.class);

    private OrderService orderService;
    private StockService stockService;
    private TappayPaymentService tappayPaymentService;
    private InvoiceService invoiceService;
    private OrderInvoiceService orderInvoiceService;


    @Autowired
    public DefaultPaymentFacade(
            OrderService orderService,
            StockService stockService,
            TappayPaymentService tappayPaymentService,
            @Qualifier(value = "ecpayInvoiceService") InvoiceService invoiceService,
            OrderInvoiceService orderInvoiceService
    )
    {
        this.orderService = orderService;
        this.stockService = stockService;
        this.tappayPaymentService = tappayPaymentService;
        this.invoiceService = invoiceService;
        this.orderInvoiceService = orderInvoiceService;
    }

    public String beginPayment(String prime, String orderCode)
    {
        final OrderEntity orderEntity = getOrderService().getOrderForCode(orderCode);
        getTappayPaymentService().validatePaymentIsValid(orderEntity);

        return getTappayPaymentService().getPaymentUrlForCreditCard(prime, orderEntity);
    }


    @Override
    public void authorizePayment(BackendNotifyRequest request)
    {
        try
        {
            final PaymentTransactionEntity paymentTransactionEntity
                    = getTappayPaymentService().authorizePayment(request.getRecTradeId());

            reduceProductStocksForOrder(paymentTransactionEntity.getOrder());

            issueInvoice(paymentTransactionEntity.getOrder());
        }
        catch (final Exception e)
        {
            log.error("Execute [authorizePayment] error!", e);
        }
    }

    private void reduceProductStocksForOrder(final OrderEntity order)
    {
        for (OrderLineItemEntity orderLineItem : order.getLineItems())
        {
            getStockService().reduceStock(orderLineItem.getVariant(), orderLineItem.getQuantity());
        }
    }

    private void issueInvoice(final OrderEntity order)
    {
        final IssueResultObj issueResultObj = getInvoiceService().postIssue(order);
        if (issueResultObj != null)
        {
            getOrderInvoiceService().saveOrderInvoiceFromEcpay(issueResultObj, order);
        }
    }

    public OrderService getOrderService()
    {
        return orderService;
    }

    public void setOrderService(OrderService orderService)
    {
        this.orderService = orderService;
    }

    public StockService getStockService()
    {
        return stockService;
    }

    public void setStockService(StockService stockService)
    {
        this.stockService = stockService;
    }

    public TappayPaymentService getTappayPaymentService()
    {
        return tappayPaymentService;
    }

    public void setTappayPaymentService(TappayPaymentService tappayPaymentService)
    {
        this.tappayPaymentService = tappayPaymentService;
    }

    public InvoiceService getInvoiceService()
    {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService)
    {
        this.invoiceService = invoiceService;
    }

    public OrderInvoiceService getOrderInvoiceService()
    {
        return orderInvoiceService;
    }

    public void setOrderInvoiceService(OrderInvoiceService orderInvoiceService)
    {
        this.orderInvoiceService = orderInvoiceService;
    }
}
