package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookcommon.service.KeyGenerator;
import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookentity.type.InvoiceStatusType;
import com.homecook.homecookentity.type.OrderStatusType;
import com.homecook.homecookentity.type.PaymentStatusType;
import com.homecook.homecookstorefront.service.strategy.CreateOrderFromCheckoutStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "createOrderFromCheckoutStrategy")
public class DefaultCreateOrderFromCheckoutStrategy implements CreateOrderFromCheckoutStrategy
{
    private ModelService modelService;
    private KeyGenerator keyGenerator;

    @Autowired
    public DefaultCreateOrderFromCheckoutStrategy(
            @Qualifier(value = "modelService") ModelService modelService,
            @Qualifier(value = "uuidKeyGenerator") KeyGenerator keyGenerator)
    {
        this.modelService = modelService;
        this.keyGenerator = keyGenerator;
    }

    @Override
    public OrderEntity createOrderFromCheckout(CheckoutEntity checkout)
    {
        final CartEntity cart = checkout.getCart();
        final List<CartLineItemEntity> cartLineItems = cart.getLineItems();
        final AddressEntity shippingAddress = checkout.getShippingAddress();

        validateParameterNotNullStandardMessage("checkout", checkout);
        validateParameterNotNullStandardMessage("cart", cart);
        validateParameterNotNullStandardMessage("cartLineItems", cartLineItems);
        validateParameterNotNullStandardMessage("shippingAddress", shippingAddress);

        OrderEntity order = new OrderEntity();

        String orderCode = generateOrderCode();
        order.setCode(orderCode);
        order.setOrderStatus(OrderStatusType.UNPAID);
        order.setSubtotal(checkout.getSubtotal());
        order.setTotalDiscounts(cart.getTotalDiscounts());
        order.setTotalPrice(checkout.getTotalPrice());
        order.setPaymentMode(checkout.getPaymentMode());
        order.setPaymentStatus(PaymentStatusType.UNPAID);
        order.setShippingCost(checkout.getShippingCost());
        order.setShippingMode(shippingAddress.getShippingMode());
        order.setShippingAddress(createShippingAddressFromAddress(checkout.getShippingAddress()));
        order.setNote(checkout.getNote());
        order.setInvoice(createOrderInvoiceFromCheckoutInvoice(orderCode, checkout.getCheckoutInvoice()));
        order.setCustomer(checkout.getCustomer());
        getModelService().save(order);

        createOrderLineItemsFromCartLineItems(order, cartLineItems);

        return order;
    }


    protected ShippingAddressEntity createShippingAddressFromAddress(AddressEntity address)
    {
        ShippingAddressEntity shippingAddress = new ShippingAddressEntity();
        shippingAddress.setCountry(address.getCountry());
        shippingAddress.setCity(address.getCity());
        shippingAddress.setDistrict(address.getDistrict());
        shippingAddress.setZipcode(address.getZipcode());
        shippingAddress.setAddress(address.getAddress());
        shippingAddress.setName(address.getName());
        shippingAddress.setPhone(address.getPhone());
        shippingAddress.setStoreId(address.getStoreId());
        shippingAddress.setStoreName(address.getStoreName());
        shippingAddress.setCustomer(address.getCustomer());
        shippingAddress.setShippingMode(address.getShippingMode());
        getModelService().save(shippingAddress);
        return shippingAddress;
    }

    protected OrderInvoiceEntity createOrderInvoiceFromCheckoutInvoice(String orderCode, CheckoutInvoiceEntity checkoutInvoice)
    {
        OrderInvoiceEntity orderInvoice = new OrderInvoiceEntity();
        orderInvoice.setInvoiceType(checkoutInvoice.getInvoiceType());
        orderInvoice.setInvoiceTitle(checkoutInvoice.getCompanyName());
        orderInvoice.setBusinessNumber(checkoutInvoice.getBusinessNumber());
        orderInvoice.setContactEmail(checkoutInvoice.getContactEmail());
        orderInvoice.setLoveCode(checkoutInvoice.getCharityLovecode());
        orderInvoice.setOrderCode(orderCode);
        orderInvoice.setInvoiceStatus(InvoiceStatusType.PENDING);
        getModelService().save(orderInvoice);
        return orderInvoice;
    }

    protected void createOrderLineItemsFromCartLineItems(OrderEntity order, List<CartLineItemEntity> cartLineItems)
    {
        List<OrderLineItemEntity> result = new ArrayList<OrderLineItemEntity>();

        for (final CartLineItemEntity cartLineItem : cartLineItems)
        {
            OrderLineItemEntity orderLineItem = new OrderLineItemEntity();
            orderLineItem.setName(cartLineItem.getName());
            orderLineItem.setPrice(cartLineItem.getPrice());
            orderLineItem.setQuantity(cartLineItem.getQuantity());
            orderLineItem.setSku(cartLineItem.getSku());
            orderLineItem.setItemKey(cartLineItem.getItemKey());
            orderLineItem.setProduct(cartLineItem.getProduct());
            orderLineItem.setVariant(cartLineItem.getVariant());
            orderLineItem.setSubtotal(cartLineItem.getSubtotal());
            orderLineItem.setTotalDiscounts(cartLineItem.getTotalDiscounts());
            orderLineItem.setTotalPrice(cartLineItem.getTotalPrice());
            orderLineItem.setOrder(order);
            result.add(orderLineItem);
        }

        getModelService().saveAll(result);
        order.setLineItems(result);
        getModelService().save(order);
    }

    protected String generateOrderCode()
    {
        final Object generatedValue = getKeyGenerator().generate();
        if (generatedValue instanceof String)
        {
            return (String) generatedValue;
        }
        else
        {
            return String.valueOf(generatedValue);
        }
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public KeyGenerator getKeyGenerator()
    {
        return keyGenerator;
    }

    public void setKeyGenerator(KeyGenerator keyGenerator)
    {
        this.keyGenerator = keyGenerator;
    }
}
