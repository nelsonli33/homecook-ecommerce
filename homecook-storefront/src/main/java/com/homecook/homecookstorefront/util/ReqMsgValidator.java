package com.homecook.homecookstorefront.util;

import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.CustomerAuthRequest;
import com.homecook.homecookstorefront.model.RegisterCustomerRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReqMsgValidator
{
    public static class AccountAuth
    {
        public static final Pattern PWD_REGEX = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$");

        public static void validateRegisterCustomerRequest(RegisterCustomerRequest registerCustomerRequest, BindingResult errors)
        {
            if (registerCustomerRequest == null)
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "RegisterCustomerRequest should not be null");
            }

            validateStringNotEmpty("account", registerCustomerRequest.getAccount(), errors);

            if (isStringContainsSpecialCharacter(registerCustomerRequest.getAccount()))
            {
                errors.rejectValue("account", "", "The customer account cannot contains special characters");
            }

            String pwd = registerCustomerRequest.getPassword();
            validateStringNotEmpty("password", pwd, errors);
            if (StringUtils.length(pwd) < 8 || StringUtils.length(pwd) > 16 || !isPasswordComplex(pwd))
            {
                errors.rejectValue("password", "", "Your password must be 8-16 characters, and include at least one letter and one number");
            }


            if (StringUtils.isNotEmpty(registerCustomerRequest.getEmail()) && !EmailValidator.getInstance().isValid(registerCustomerRequest.getEmail()))
            {
                errors.rejectValue("email", "", "Incorrect Email format");
            }

            if (errors.hasErrors())
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
            }
        }


        public static void validateCustomerAuthRequest(CustomerAuthRequest customerAuthRequest, BindingResult errors)
        {
            if (customerAuthRequest == null)
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "CustomerAuthRequest should not be null");
            }
            validateStringNotEmpty("uid", customerAuthRequest.getUid(), errors);
            validateStringNotEmpty("password", customerAuthRequest.getPassword(), errors);

            if (errors.hasErrors())
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "Invalid fields", errors);
            }
        }


        private static boolean isPasswordComplex(String password)
        {
            Matcher matcher = PWD_REGEX.matcher(password);
            return matcher.matches();
        }
    }

    private static void validateStringNotEmpty(String field, String inputString, Errors errors)
    {
        if (StringUtils.isEmpty(inputString))
        {
            errors.rejectValue(field, "", field + " cannot be empty");
        }
    }

    private static boolean isStringContainsSpecialCharacter(String inputString)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        final Matcher matcher = pattern.matcher(inputString);
        return matcher.find();
    }
}
