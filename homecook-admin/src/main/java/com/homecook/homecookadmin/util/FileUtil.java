package com.homecook.homecookadmin.util;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil
{

    public static void checkValidImageFile(MultipartFile file) {
        String mimeType = file.getContentType();
        if (!mimeType.startsWith("image/"))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.FILE_TYPE_NOT_SUPPORT, "only support image file.");
        }
    }
}
