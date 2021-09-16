package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.ProductImageData;
import org.springframework.web.multipart.MultipartFile;

public interface AdminProductFacade
{
    ProductImageData uploadProductImage(MultipartFile file);
}
