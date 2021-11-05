package com.homecook.homecookstorefront.util.validator;

import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.AddToCartRequest;
import com.homecook.homecookstorefront.model.UpdateCartRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component(value = "cartReqMsgValidator")
public class CartReqMsgValidator extends AbstractReqMsgValidator
{
    public void validateAddToCartRequest(AddToCartRequest addToCartRequest, BindingResult errors)
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

    public void validateUpdateCartRequest(UpdateCartRequest updateCartRequest, BindingResult errors)
    {
        if (updateCartRequest == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "UpdateCartRequest should not be null");
        }

        if (updateCartRequest.getProductId() == null)
        {
            errors.rejectValue("productId", "", "Product Id cannot be null");
        }

        if (updateCartRequest.getQty() < 0)
        {
            errors.rejectValue("qty", "", "Quantity only accept positive number or zero");
        }

        if (errors.hasErrors())
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
        }
    }
}
