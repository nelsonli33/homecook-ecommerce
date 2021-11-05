package com.homecook.homecookstorefront.util.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.CreateAddressRequest;
import com.homecook.homecookstorefront.service.ShippingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component(value = "addressReqMsgValidator")
public class AddressReqMsgValidator extends AbstractReqMsgValidator
{
    private ShippingService shippingService;

    @Autowired
    public AddressReqMsgValidator(ShippingService shippingService)
    {
        this.shippingService = shippingService;
    }

    public void validateCreateAddressRequest(CreateAddressRequest createAddressRequest, BindingResult errors)
    {
        if (createAddressRequest == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "CreateAddressRequest should not be null");
        }

        validateStringNotEmpty("name", createAddressRequest.getName(), errors);
        validateStringNotEmpty("phone", createAddressRequest.getPhone(), errors);
        validatePhoneField(createAddressRequest.getPhone(), errors);

        validateCreateStandardAddress(createAddressRequest, errors);
        validateCreateCVSAddress(createAddressRequest, errors);

        if (errors.hasErrors())
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
        }
    }


    private void validateCreateStandardAddress(CreateAddressRequest createAddressRequest, BindingResult errors)
    {
        if (!shippingService.getAllCVSCodes().contains(createAddressRequest.getCode()))
        {
            validateStringNotEmpty("code", createAddressRequest.getCode(), errors);
            validateStringNotEmpty("city", createAddressRequest.getCity(), errors);
            validateStringNotEmpty("district", createAddressRequest.getDistrict(), errors);
            validateStringNotEmpty("zipcode", createAddressRequest.getZipcode(), errors);
            validateStringNotEmpty("address", createAddressRequest.getAddress(), errors);
        }
    }

    private void validateCreateCVSAddress(CreateAddressRequest createAddressRequest, BindingResult errors)
    {
        if (shippingService.getAllCVSCodes().contains(createAddressRequest.getCode()))
        {
            validateStringNotEmpty("address", createAddressRequest.getAddress(), errors);
            validateStringNotEmpty("storeId", createAddressRequest.getStoreId(), errors);
            validateStringNotEmpty("storeName", createAddressRequest.getStoreName(), errors);
        }
    }

    private void validatePhoneField(String cellphone, BindingResult errors)
    {
        if (StringUtils.isNotEmpty(cellphone))
        {

            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

            try
            {
                final Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(cellphone, LocaleContextHolder.getLocale().getCountry());
                final PhoneNumberUtil.PhoneNumberType numberType = phoneNumberUtil.getNumberType(phoneNumber);
                if (numberType != PhoneNumberUtil.PhoneNumberType.MOBILE || !phoneNumberUtil.isValidNumber(phoneNumber))
                {
                    errors.rejectValue("phone", "", "incorrect cellphone format");
                }
            }
            catch (NumberParseException e)
            {
                errors.rejectValue("phone", "incorrect cellphone format");
                e.printStackTrace();
            }
        }
    }
}
