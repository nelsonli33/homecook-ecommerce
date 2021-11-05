package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.service.CartService;
import com.homecook.homecookstorefront.service.CheckoutFactory;
import com.homecook.homecookstorefront.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "checkoutFactory")
public class DefaultCheckoutFactory implements CheckoutFactory
{
    private CartService cartService;
    private ShippingService shippingService;
    private ModelService modelService;

    @Autowired
    public DefaultCheckoutFactory(CartService cartService, ShippingService shippingService, ModelService modelService)
    {
        this.cartService = cartService;
        this.shippingService = shippingService;
        this.modelService = modelService;
    }

    @Override
    public CheckoutEntity createCheckout()
    {

        final CartEntity cart = getCartService().getCartForCurrentCustomer();
        final CustomerEntity currentCustomer = cart.getCustomer();

        CheckoutEntity checkout = new CheckoutEntity();
        checkout.setCode(cart.getCode());
        checkout.setCart(cart);
        checkout.setCustomer(currentCustomer);
        checkout.setPaymentMode(currentCustomer.getDefaultPaymentMode());
        checkout.setShippingAddress(getDefaultShippingAddress(cart));

        getModelService().save(checkout);

        return checkout;
    }

    private AddressEntity getDefaultShippingAddress(CartEntity cart)
    {
        final CustomerEntity currentCustomer = cart.getCustomer();
        final AddressEntity lastCheckoutShipmentAddress = currentCustomer.getLastCheckoutShipmentAddress();

        // Ensure that the shipping address is in the set of supported addresses
        if (lastCheckoutShipmentAddress != null)
        {
            for (AddressEntity address : getShippingService().getSupportedShippingAddressesForCart(cart))
            {
                if (address.getId().equals(lastCheckoutShipmentAddress.getId()))
                {
                    return address;
                }
            }
        }
        return null;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public ShippingService getShippingService()
    {
        return shippingService;
    }

    public void setShippingService(ShippingService shippingService)
    {
        this.shippingService = shippingService;
    }
}
