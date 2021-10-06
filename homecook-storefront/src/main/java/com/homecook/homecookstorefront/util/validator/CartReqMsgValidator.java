package com.homecook.homecookstorefront.util.validator;

import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.AddToCartRequest;
import org.springframework.validation.BindingResult;

public class CartReqMsgValidator
{
    public static void validateAddToCartRequest(AddToCartRequest addToCartRequest, BindingResult errors)
    {
        if (addToCartRequest == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "AddToCartRequest should not be null");
        }

        if (addToCartRequest.getProductId() == null)
        {
            errors.rejectValue("productId", "", "Product Id cannot be null");
        }

        if (addToCartRequest.getQty() < 0)
        {
            errors.rejectValue("qty", "", "Quantity only accept positive number");
        }

        if (errors.hasErrors())
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
        }
    }
}
