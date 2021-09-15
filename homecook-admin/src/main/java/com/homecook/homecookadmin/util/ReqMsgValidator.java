package com.homecook.homecookadmin.util;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.form.CategoryForm;
import org.apache.commons.lang3.StringUtils;

public class ReqMsgValidator
{
    public static void validateCategoryForm(CategoryForm categoryForm) {
        if (categoryForm == null) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "categoryForm should not be null");
        }

        if (StringUtils.isEmpty(categoryForm.getName())) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.INVALID_REQUEST_BODY, "category name should not be null or empty");
        }
    }
}
