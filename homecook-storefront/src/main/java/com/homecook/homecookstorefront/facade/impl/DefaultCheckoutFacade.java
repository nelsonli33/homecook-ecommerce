package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.*;
import com.homecook.homecookstorefront.dto.*;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.facade.CheckoutFacade;
import com.homecook.homecookstorefront.facade.CustomerFacade;
import com.homecook.homecookstorefront.facade.mapper.*;
import com.homecook.homecookstorefront.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component(value = "checkoutFacade")
public class DefaultCheckoutFacade implements CheckoutFacade
{
    private CustomerFacade customerFacade;
    private CheckoutService checkoutService;
    private CartService cartService;
    private ShippingService shippingService;
    private PaymentService paymentService;
    private CustomerService customerService;
    private CustomerAccountService customerAccountService;
    private StockService stockService;
    private CustomerMapper customerMapper;
    private ShippingModeMapper shippingModeMapper;
    private PaymentModeMapper paymentModeMapper;
    private CheckoutMapper checkoutMapper;
    private OrderMapper orderMapper;

    @Autowired
    public DefaultCheckoutFacade(
            CustomerFacade customerFacade,
            CheckoutService checkoutService,
            CartService cartService,
            ShippingService shippingService,
            PaymentService paymentService,
            CustomerService customerService,
            CustomerAccountService customerAccountService,
            StockService stockService,
            CustomerMapper customerMapper,
            ShippingModeMapper shippingModeMapper,
            PaymentModeMapper paymentModeMapper,
            CheckoutMapper checkoutMapper,
            OrderMapper orderMapper)
    {
        this.customerFacade = customerFacade;
        this.checkoutService = checkoutService;
        this.cartService = cartService;
        this.shippingService = shippingService;
        this.paymentService = paymentService;
        this.customerService = customerService;
        this.customerAccountService = customerAccountService;
        this.stockService = stockService;
        this.customerMapper = customerMapper;
        this.shippingModeMapper = shippingModeMapper;
        this.paymentModeMapper = paymentModeMapper;
        this.checkoutMapper = checkoutMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public CheckoutDTO getCheckout()
    {
        final CheckoutEntity checkout = getCheckoutService().getCheckout();
        final CheckoutDTO checkoutDTO = getCheckoutMapper().convertToCheckoutDTO(checkout);


        checkoutDTO.setPaymentMethods(getSupportedPaymentMethods());
        checkoutDTO.setShippingMethods(getSupportedShippingMethods());
        checkoutDTO.setInvoiceSetting(getCustomerFacade().getCustomerInvoiceSetting());

        return checkoutDTO;
    }

    @Override
    public void appliedPaymentMethod(String paymentMethodCode)
    {
        if (StringUtils.isEmpty(paymentMethodCode))
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CHECKOUT_PAYMENT_METHOD_ERROR, "Must provided a payment method to checkout");
        }
        final CheckoutEntity checkout = getCheckoutService().getCheckout();
        final PaymentModeEntity paymentMode = getPaymentService().getPaymentModeForCode(paymentMethodCode);
        getCheckoutService().appliedPaymentMethod(checkout, paymentMode);
    }

    @Override
    public void appliedShippingAddress(Long addressId)
    {
        final CheckoutEntity checkout = getCheckoutService().getCheckout();
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
        final AddressEntity address = getCustomerAccountService().getAddressForId(currentCustomer, addressId);

        getCheckoutService().appliedShippingAddress(checkout, address);
    }

    @Override
    public void appliedInvoice(CheckoutInvoiceDTO checkoutInvoiceDTO)
    {
        if (checkoutInvoiceDTO.getSaveInvoice())
        {
            switch (checkoutInvoiceDTO.getInvoiceType())
            {
                case COMPANY:
                    final CompanyInvoiceDTO companyInvoiceDTO
                            = getCheckoutMapper().convertToCompanyInvoiceDTO(checkoutInvoiceDTO);
                    getCustomerFacade().setCompanyInvoiceSetting(companyInvoiceDTO);
                    break;

                case DONATION:
                    final DonationInvoiceDTO donationInvoiceDTO
                            = getCheckoutMapper().convertToDonationInvoiceDTO(checkoutInvoiceDTO);
                    getCustomerFacade().setDonationInvoiceSetting(donationInvoiceDTO);
                    break;
            }
        }

        final CheckoutInvoiceEntity checkoutInvoice
                = getCheckoutMapper().convertToCheckoutInvoiceEntity(checkoutInvoiceDTO);

        final CheckoutEntity checkout = getCheckoutService().getCheckout();
        getCheckoutService().appliedCheckoutInvoice(checkout, checkoutInvoice);
    }

    @Override
    @Transactional
    public OrderDTO placeOrder()
    {
        final CheckoutEntity checkout = getCheckoutService().getCheckout();
        if (checkout != null)
        {
            beforePlaceOrder(checkout);
            OrderEntity order = placeOrder(checkout);
            afterPlaceOrder(checkout);
            if (order != null)
            {
                reserveStockAfterPlaceOrder(order);
                return getOrderMapper().convertToOrderDTO(order);
            }
        }
        return null;
    }


    private void beforePlaceOrder(final CheckoutEntity checkoutEntity)
    {
        // Do nothing, extension point
    }

    private OrderEntity placeOrder(final CheckoutEntity checkoutEntity)
    {
        return getCheckoutService().placeOrder(checkoutEntity);
    }

    private void afterPlaceOrder(CheckoutEntity checkoutEntity)
    {
        getCartService().removeCart();
        getCheckoutService().removeCheckout(checkoutEntity);
    }

    private void reserveStockAfterPlaceOrder(OrderEntity order)
    {
        for (final OrderLineItemEntity orderLineItem : order.getLineItems())
        {
            final boolean reserveStock
                    = getStockService().reserveStock(orderLineItem.getVariant(), orderLineItem.getQuantity());

            if (!reserveStock)
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.INSUFFICIENT_STOCK_ERROR, "Insufficient stock for variant with id: " + orderLineItem.getVariantId() + " no stock.");
            }
        }
    }


    @Override
    public List<PaymentModeDTO> getSupportedPaymentMethods()
    {
        final List<PaymentModeEntity> activePaymentModes = getPaymentService().getActivePaymentModes();
        return getPaymentModeMapper().convertToPaymentModeDTOs(activePaymentModes);
    }

    @Override
    public List<ShippingModeDTO> getSupportedShippingMethods()
    {
        final CartEntity cart = getCart();
        if (cart == null)
        {
            return Collections.emptyList();
        }

        final List<ShippingModeEntity> supportedShippingModes
                = getShippingService().getSupportedShippingModesForCart(cart);

        final List<ShippingModeDTO> shippingModeDTOs
                = getShippingModeMapper().convertToShippingModeDTOs(supportedShippingModes);

        return shippingModeDTOs;
    }

    @Override
    public List<AddressDTO> getSupportedShippingAddresses()
    {
        final CartEntity cart = getCart();

        final List<AddressEntity> addresses =
                getShippingService().getSupportedShippingAddressesForCart(cart);

        return getCustomerMapper().convertToAddressDTOs(addresses);
    }

    @Override
    public boolean hasValidCart()
    {
        final CartEntity cart = getCart();
        return cart.getLineItems() != null && !cart.getLineItems().isEmpty();
    }

    @Override
    public void validateCheckout()
    {
        final CheckoutEntity checkout = getCheckoutService().getCheckout();
        getCheckoutService().validateCheckout(checkout);
    }


    protected CartEntity getCart()
    {
        return getCartService().getCartForCurrentCustomer();
    }


    public CustomerFacade getCustomerFacade()
    {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacade customerFacade)
    {
        this.customerFacade = customerFacade;
    }

    public CheckoutService getCheckoutService()
    {
        return checkoutService;
    }

    public void setCheckoutService(CheckoutService checkoutService)
    {
        this.checkoutService = checkoutService;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public ShippingService getShippingService()
    {
        return shippingService;
    }

    public void setShippingService(ShippingService shippingService)
    {
        this.shippingService = shippingService;
    }

    public PaymentService getPaymentService()
    {
        return paymentService;
    }

    public void setPaymentService(PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CustomerAccountService getCustomerAccountService()
    {
        return customerAccountService;
    }

    public void setCustomerAccountService(CustomerAccountService customerAccountService)
    {
        this.customerAccountService = customerAccountService;
    }

    public StockService getStockService()
    {
        return stockService;
    }

    public void setStockService(StockService stockService)
    {
        this.stockService = stockService;
    }

    public CustomerMapper getCustomerMapper()
    {
        return customerMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper)
    {
        this.customerMapper = customerMapper;
    }

    public ShippingModeMapper getShippingModeMapper()
    {
        return shippingModeMapper;
    }

    public void setShippingModeMapper(ShippingModeMapper shippingModeMapper)
    {
        this.shippingModeMapper = shippingModeMapper;
    }

    public PaymentModeMapper getPaymentModeMapper()
    {
        return paymentModeMapper;
    }

    public void setPaymentModeMapper(PaymentModeMapper paymentModeMapper)
    {
        this.paymentModeMapper = paymentModeMapper;
    }

    public CheckoutMapper getCheckoutMapper()
    {
        return checkoutMapper;
    }

    public void setCheckoutMapper(CheckoutMapper checkoutMapper)
    {
        this.checkoutMapper = checkoutMapper;
    }

    public OrderMapper getOrderMapper()
    {
        return orderMapper;
    }

    public void setOrderMapper(OrderMapper orderMapper)
    {
        this.orderMapper = orderMapper;
    }
}
