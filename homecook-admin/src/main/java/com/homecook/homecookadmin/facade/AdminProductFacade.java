package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.ProductDTO;
import com.homecook.homecookadmin.dto.ProductImageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface AdminProductFacade
{
    ProductDTO createProduct(ProductDTO productDTO);

    ProductImageDTO uploadProductImage(MultipartFile file);
}
