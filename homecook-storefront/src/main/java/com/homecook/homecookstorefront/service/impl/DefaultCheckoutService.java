package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.repository.CheckoutRepository;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.*;
import com.homecook.homecookstorefront.service.strategy.PlaceOrderStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "checkoutService")
public class DefaultCheckoutService implements CheckoutService
{
    private CheckoutFactory checkoutFactory;
    private CustomerService customerService;
    private CheckoutRepository checkoutRepository;
    private CalculationService calculationService;
    private ShippingService shippingService;
    private ModelService modelService;
    private PlaceOrderStrategy placeOrderStrategy;


    @Autowired
    public DefaultCheckoutService(
            CheckoutFactory checkoutFactory,
            CustomerService customerService,
            CheckoutRepository checkoutRepository,
            CalculationService calculationService,
            ShippingService shippingService,
            ModelService modelService,
            PlaceOrderStrategy placeOrderStrategy)
    {
        this.checkoutFactory = checkoutFactory;
        this.customerService = customerService;
        this.checkoutRepository = checkoutRepository;
        this.calculationService = calculationService;
        this.shippingService = shippingService;
        this.modelService = modelService;
        this.placeOrderStrategy = placeOrderStrategy;
    }


    @Override
    public CheckoutEntity getCheckout()
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        CheckoutEntity checkout
                = getCheckoutRepository().findCheckoutEntityByCustomer(currentCustomer);

        if (checkout == null)
        {
            checkout = getCheckoutFactory().createCheckout();
        }

        getCalculationService().calculate(checkout);

        return checkout;
    }

    @Override
    public void appliedShippingAddress(CheckoutEntity checkoutEntity, AddressEntity addressEntity)
    {
        validateParameterNotNullStandardMessage("checkoutEntity", checkoutEntity);
        validateParameterNotNullStandardMessage("address", addressEntity);

        if (isValidShippingAddress(checkoutEntity.getCart(), addressEntity))
        {
            checkoutEntity.setShippingAddress(addressEntity);
            getCalculationService().calculate(checkoutEntity);
            getModelService().save(checkoutEntity);
        }
        else
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CHECKOUT_APPLIED_SHIPPING_ADDRESS_ERROR, "Could not be applied shipping address with id: " + addressEntity.getId() + " for checkout, please choose other address.");
        }
    }

    @Override
    public void appliedPaymentMethod(CheckoutEntity checkoutEntity, PaymentModeEntity paymentModeEntity)
    {
        validateParameterNotNullStandardMessage("checkoutEntity", checkoutEntity);
        validateParameterNotNullStandardMessage("paymentModeEntity", paymentModeEntity);

        checkoutEntity.setPaymentMode(paymentModeEntity);
        getModelService().save(checkoutEntity);
    }

    @Override
    public void appliedCheckoutInvoice(CheckoutEntity checkoutEntity, CheckoutInvoiceEntity checkoutInvoiceEntity)
    {
        validateParameterNotNullStandardMessage("checkoutEntity", checkoutEntity);
        validateParameterNotNullStandardMessage("checkoutInvoiceEntity", checkoutInvoiceEntity);

        final CheckoutInvoiceEntity checkoutInvoice = checkoutEntity.getCheckoutInvoice();

        if (checkoutInvoice != null)
        {
            // directly overwrite old checkout invoice info
            checkoutInvoice.setContactEmail(checkoutInvoiceEntity.getContactEmail());
            checkoutInvoice.setInvoiceType(checkoutInvoiceEntity.getInvoiceType());
            checkoutInvoice.setCompanyName(checkoutInvoiceEntity.getCompanyName());
            checkoutInvoice.setBusinessNumber(checkoutInvoiceEntity.getBusinessNumber());
            checkoutInvoice.setCharityLovecode(checkoutInvoiceEntity.getCharityLovecode());
            checkoutInvoice.setCharityName(checkoutInvoiceEntity.getCharityName());
            checkoutInvoice.setCheckoutCode(checkoutEntity.getCode());

            getModelService().save(checkoutInvoice);
        }
        else
        {
            getModelService().save(checkoutInvoiceEntity);
            checkoutEntity.setCheckoutInvoice(checkoutInvoiceEntity);
            getModelService().save(checkoutEntity);
        }
    }

    @Override
    public void removeCheckout(CheckoutEntity checkoutEntity)
    {
        final CheckoutInvoiceEntity checkoutInvoice = checkoutEntity.getCheckoutInvoice();
        getModelService().remove(checkoutInvoice);
        getModelService().remove(checkoutEntity);
    }


    @Override
    public OrderEntity placeOrder(CheckoutEntity checkoutEntity)
    {
        return getPlaceOrderStrategy().placeOrder(checkoutEntity);
    }

    @Override
    public void validateCheckout(CheckoutEntity checkoutEntity)
    {
        final AddressEntity shippingAddress = checkoutEntity.getShippingAddress();
        if (shippingAddress == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CHECKOUT_MISSING_SHIPPING_ADDRESS, "Must provide shipping address to checkout");
        }

        final ShippingModeEntity shippingMode = shippingAddress.getShippingMode();
        if (shippingMode == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CHECKOUT_MISSING_SHIPPING_METHODS, "Must provide shipping methods to checkout");
        }

        final PaymentModeEntity paymentMode = checkoutEntity.getPaymentMode();
        if (paymentMode == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CHECKOUT_MISSING_PAYMENT_METHODS, "Must provide payment methods to checkout");
        }
    }

    private boolean isValidShippingAddress(CartEntity cartEntity, AddressEntity addressEntity)
    {
        validateParameterNotNullStandardMessage("cart", cartEntity);
        validateParameterNotNullStandardMessage("address", addressEntity);

        final List<AddressEntity> addresses
                = getShippingService().getSupportedShippingAddressesForCart(cartEntity);

        final Optional<AddressEntity> address
                = addresses.stream().filter(addr -> addr.getId().equals(addressEntity.getId())).findAny();

        return address.isPresent();
    }

    public CheckoutFactory getCheckoutFactory()
    {
        return checkoutFactory;
    }

    public void setCheckoutFactory(CheckoutFactory checkoutFactory)
    {
        this.checkoutFactory = checkoutFactory;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CheckoutRepository getCheckoutRepository()
    {
        return checkoutRepository;
    }

    public void setCheckoutRepository(CheckoutRepository checkoutRepository)
    {
        this.checkoutRepository = checkoutRepository;
    }

    public CalculationService getCalculationService()
    {
        return calculationService;
    }

    public void setCalculationService(CalculationService calculationService)
    {
        this.calculationService = calculationService;
    }

    public ShippingService getShippingService()
    {
        return shippingService;
    }

    public void setShippingService(ShippingService shippingService)
    {
        this.shippingService = shippingService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public PlaceOrderStrategy getPlaceOrderStrategy()
    {
        return placeOrderStrategy;
    }

    public void setPlaceOrderStrategy(PlaceOrderStrategy placeOrderStrategy)
    {
        this.placeOrderStrategy = placeOrderStrategy;
    }
}
