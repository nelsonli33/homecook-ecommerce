package com.homecook.homecookadmin.util.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

public abstract class AbstractReqMsgValidator
{
    protected void validateStringNotEmpty(String field, String inputString, Errors errors)
    {
        if (StringUtils.isEmpty(inputString))
        {
            errors.rejectValue(field, "", field + " cannot be empty");
        }
    }

}
