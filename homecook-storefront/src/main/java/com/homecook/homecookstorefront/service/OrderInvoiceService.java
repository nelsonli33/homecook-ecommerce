package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.entity.OrderInvoiceEntity;
import ecpay.invoice.integration.domain.IssueResultObj;

public interface OrderInvoiceService
{
    OrderInvoiceEntity saveOrderInvoiceFromEcpay(IssueResultObj resultObj, OrderEntity order);
}
