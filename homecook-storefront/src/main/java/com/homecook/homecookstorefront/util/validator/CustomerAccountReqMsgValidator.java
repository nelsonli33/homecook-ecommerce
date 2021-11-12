package com.homecook.homecookstorefront.util.validator;

import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.UpdatePasswordRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component(value = "customerAccountReqMsgValidator")
public class CustomerAccountReqMsgValidator extends AbstractReqMsgValidator
{
    public void validateUpdatePasswordRequest(UpdatePasswordRequest updatePasswordRequest, BindingResult errors)
    {
        validateStringNotEmpty("currentPassword", updatePasswordRequest.getCurrentPassword(), errors);
        validateStringNotEmpty("newPassword", updatePasswordRequest.getNewPassword(), errors);
        validateStringNotEmpty("checkNewPassword", updatePasswordRequest.getCheckNewPassword(), errors);

        if (!updatePasswordRequest.getCheckNewPassword().equals(updatePasswordRequest.getNewPassword()))
        {
            errors.rejectValue("checkNewPassword", "", "Please reconfirm your password.");
        }

        if (errors.hasErrors())
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
        }
    }
}
