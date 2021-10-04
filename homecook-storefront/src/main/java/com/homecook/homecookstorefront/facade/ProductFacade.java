package com.homecook.homecookstorefront.facade;


import com.homecook.homecookstorefront.dto.ProductDTO;

public interface ProductFacade
{
    ProductDTO getProductDetails(Long productId);
}
