package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.ProductImageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AdminProductFacade
{
    ProductImageDTO uploadProductImage(MultipartFile file);
}
