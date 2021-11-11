package com.homecook.homecookadmin.util.validator;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.model.CreateVoucherRequest;
import com.homecook.homecookadmin.model.UpdateVoucherRequest;
import com.homecook.homecookentity.type.VoucherUseType;
import com.homecook.homecookentity.type.VoucherValueType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component(value = "voucherReqMsgValidator")
public class VoucherReqMsgValidator extends AbstractReqMsgValidator
{
    public void validateCreateVoucherRequest(CreateVoucherRequest createVoucherRequest, BindingResult errors)
    {
        if (createVoucherRequest == null)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "CreateVoucherRequest should not be null");
        }

        validateStringNotEmpty("code", createVoucherRequest.getCode(), errors);
        validateStringNotEmpty("startAt", createVoucherRequest.getStartAt(), errors);
        validateStringNotEmpty("endAt", createVoucherRequest.getEndAt(), errors);

        switch (VoucherUseType.valueOf(createVoucherRequest.getUseType()))
        {
            case SPECIFIED_CATEGORY:
                if (createVoucherRequest.getAppliedCategoryIds() == null || createVoucherRequest.getAppliedCategoryIds().size() == 0)
                {
                    errors.rejectValue("appliedCategoryIds", "", "Voucher useType is 2, the voucher must given category ids");
                }
                break;
            case SPECIFIED_PRODUCT:
                if (createVoucherRequest.getAppliedProductIds() == null || createVoucherRequest.getAppliedProductIds().size() == 0)
                {
                    errors.rejectValue("appliedProductIds", "", "Voucher useType is 3, the voucher must given product ids");
                }
                break;
        }

        switch (VoucherValueType.valueOf(createVoucherRequest.getValueType()))
        {
            case FIX_AMOUNT:
                if (createVoucherRequest.getValue() == null)
                {
                    errors.rejectValue("value", "", "Missing 'value'");
                }

                if (createVoucherRequest.getValue() != null && createVoucherRequest.getValue() <= 0)
                {
                    errors.rejectValue("value", "", "'value' must be positive and non-zero");
                }

                if (createVoucherRequest.getMaxDiscountValue() != null)
                {
                    errors.rejectValue("value", "", "Voucher use type is fix amount. 'maxDiscountValue' no need to provide value.");
                }
                break;
            case DISCOUNT_PERCENTAGE:
                if (createVoucherRequest.getPercentage() == null)
                {
                    errors.rejectValue("percentage", "", "Missing 'percentage'");
                }

                if (createVoucherRequest.getPercentage() != null && createVoucherRequest.getPercentage() <= 0)
                {
                    errors.rejectValue("percentage", "", "'percentage' must be positive and non-zero");
                }

                if (createVoucherRequest.getMaxDiscountValue() == null)
                {
                    errors.rejectValue("value", "", "Voucher use type is percentage discount. Missing 'maxDiscountValue'.");
                }
                break;
        }

        if (createVoucherRequest.getMinimumAmount() == null)
        {
            errors.rejectValue("minimumAmount", "", "Missing 'minimumAmount'");
        }

        if (createVoucherRequest.getMinimumAmount() != null && createVoucherRequest.getMinimumAmount() < 0)
        {
            errors.rejectValue("minimumAmount", "", "'minimumAmount' must be positive and non-zero");
        }

        if (createVoucherRequest.getUsageCount() <= 0)
        {
            errors.rejectValue("usageCount", "", "'usageCount' must be positive");
        }

        if (errors.hasErrors())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
        }
    }


    public void validateUpdateVoucherRequest(UpdateVoucherRequest updateVoucherRequest, BindingResult errors)
    {
        if (updateVoucherRequest == null)
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "UpdateVoucherRequest should not be null");
        }

        validateStringNotEmpty("startAt", updateVoucherRequest.getStartAt(), errors);
        validateStringNotEmpty("endAt", updateVoucherRequest.getEndAt(), errors);

        switch (VoucherValueType.valueOf(updateVoucherRequest.getValueType()))
        {
            case FIX_AMOUNT:
                if (updateVoucherRequest.getValue() == null)
                {
                    errors.rejectValue("value", "", "Missing 'value'");
                }

                if (updateVoucherRequest.getValue() != null && updateVoucherRequest.getValue() <= 0)
                {
                    errors.rejectValue("value", "", "'value' must be positive and non-zero");
                }

                if (updateVoucherRequest.getMaxDiscountValue() != null)
                {
                    errors.rejectValue("value", "", "Voucher use type is fix amount. 'maxDiscountValue' no need to provide value.");
                }
                break;
            case DISCOUNT_PERCENTAGE:
                if (updateVoucherRequest.getPercentage() == null)
                {
                    errors.rejectValue("percentage", "", "Missing 'percentage'");
                }

                if (updateVoucherRequest.getPercentage() != null && updateVoucherRequest.getPercentage() <= 0)
                {
                    errors.rejectValue("percentage", "", "'percentage' must be positive and non-zero");
                }

                if (updateVoucherRequest.getMaxDiscountValue() == null)
                {
                    errors.rejectValue("value", "", "Voucher use type is percentage discount. Missing 'maxDiscountValue'.");
                }
                break;
        }

        if (updateVoucherRequest.getUsageCount() <= 0)
        {
            errors.rejectValue("usageCount", "", "'usageCount' must be positive");
        }

        if (errors.hasErrors())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
        }
    }


}
