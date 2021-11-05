package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.service.CustomerService;
import com.homecook.homecookstorefront.service.OrderService;
import com.homecook.homecookstorefront.service.strategy.PlaceOrderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "placeOrderStrategy")
public class DefaultPlaceOrderStrategy implements PlaceOrderStrategy
{
    private OrderService orderService;
    private CustomerService customerService;
    private ModelService modelService;

    @Autowired
    public DefaultPlaceOrderStrategy(OrderService orderService, CustomerService customerService, ModelService modelService)
    {
        this.orderService = orderService;
        this.customerService = customerService;
        this.modelService = modelService;
    }

    @Override
    public OrderEntity placeOrder(CheckoutEntity checkout)
    {
        validateParameterNotNullStandardMessage("checkout", checkout);
        final OrderEntity order = getOrderService().createOrderFromCheckout(checkout);

        if (order != null)
        {
            final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
            currentCustomer.setLastCheckoutShipmentAddress(checkout.getShippingAddress());
            currentCustomer.setDefaultInvoiceType(order.getInvoice().getInvoiceType());
            currentCustomer.setDefaultPaymentMode(order.getPaymentMode());
            getModelService().save(currentCustomer);
        }

        return order;
    }


    public OrderService getOrderService()
    {
        return orderService;
    }

    public void setOrderService(OrderService orderService)
    {
        this.orderService = orderService;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
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
