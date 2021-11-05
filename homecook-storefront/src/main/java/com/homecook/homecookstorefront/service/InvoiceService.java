package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.OrderEntity;
import ecpay.invoice.integration.domain.IssueResultObj;

public interface InvoiceService
{
    IssueResultObj postIssue(final OrderEntity order);

    boolean checkLoveCode(String loveCode);
}
