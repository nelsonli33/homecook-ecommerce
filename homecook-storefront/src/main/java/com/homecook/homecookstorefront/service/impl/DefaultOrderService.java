package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.repository.OrderRepository;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.CustomerService;
import com.homecook.homecookstorefront.service.OrderService;
import com.homecook.homecookstorefront.service.strategy.CreateOrderFromCheckoutStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service(value = "orderService")
public class DefaultOrderService implements OrderService
{
    private CustomerService customerService;
    private CreateOrderFromCheckoutStrategy createOrderFromCheckoutStrategy;
    private OrderRepository orderRepository;

    @Autowired
    public DefaultOrderService(CustomerService customerService, CreateOrderFromCheckoutStrategy createOrderFromCheckoutStrategy, OrderRepository orderRepository)
    {
        this.customerService = customerService;
        this.createOrderFromCheckoutStrategy = createOrderFromCheckoutStrategy;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity getOrderForCode(String orderCode)
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        for (final OrderEntity order : currentCustomer.getOrder())
        {
            if (order.getCode().equals(orderCode))
            {
                return order;
            }
        }

        throw new StorefrontServerRuntimeException(InternalErrorCode.ORDER_CODE_NOT_FOUND, "Order code: " + orderCode + "not found.");
    }

    @Override
    public OrderEntity getOrderForCodeNoAuth(String orderCode)
    {
        final Optional<OrderEntity> order = getOrderRepository().findOrderEntityByCode(orderCode);
        if (order.isPresent())
        {
            return order.get();
        }
        throw new StorefrontServerRuntimeException(InternalErrorCode.ORDER_CODE_NOT_FOUND, "Order code: " + orderCode + "not found.");
    }

    @Transactional
    @Override
    public OrderEntity createOrderFromCheckout(CheckoutEntity checkout)
    {
        return getCreateOrderFromCheckoutStrategy().createOrderFromCheckout(checkout);
    }


    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CreateOrderFromCheckoutStrategy getCreateOrderFromCheckoutStrategy()
    {
        return createOrderFromCheckoutStrategy;
    }

    public void setCreateOrderFromCheckoutStrategy(CreateOrderFromCheckoutStrategy createOrderFromCheckoutStrategy)
    {
        this.createOrderFromCheckoutStrategy = createOrderFromCheckoutStrategy;
    }

    public OrderRepository getOrderRepository()
    {
        return orderRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }
}
