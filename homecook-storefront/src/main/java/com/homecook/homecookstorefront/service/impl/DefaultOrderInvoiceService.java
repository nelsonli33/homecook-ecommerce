package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.entity.OrderInvoiceEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookentity.type.InvoiceStatusType;
import com.homecook.homecookstorefront.service.OrderInvoiceService;
import ecpay.invoice.integration.domain.IssueResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service(value = "orderInvoiceService")
public class DefaultOrderInvoiceService implements OrderInvoiceService
{
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ModelService modelService;

    @Autowired
    public DefaultOrderInvoiceService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    @Override
    public OrderInvoiceEntity saveOrderInvoiceFromEcpay(IssueResultObj resultObj, OrderEntity order)
    {
        final OrderInvoiceEntity invoice = order.getInvoice();
        if (resultObj.getRtnCode() == 1)
        {
            invoice.setInvoiceNumber(resultObj.getInvoiceNumber());
            invoice.setInvoiceStatus(InvoiceStatusType.SUCCESS);
            try
            {
                final Date parsedInvoiceDate = simpleDateFormat.parse(resultObj.getInvoiceDate());
                invoice.setInvoiceDate(parsedInvoiceDate);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            invoice.setInvoiceStatus(InvoiceStatusType.FAILURE);
        }
        invoice.setGatewayMessage(resultObj.getRtnMsg());
        getModelService().save(invoice);
        return invoice;
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
