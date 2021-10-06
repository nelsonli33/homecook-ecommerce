package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookstorefront.dto.AddToCartDTO;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.service.CartService;
import com.homecook.homecookstorefront.service.ProductService;
import com.homecook.homecookstorefront.service.SKUProductFactory;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class CommerceCartMapper
{
    private CartService cartService;
    private ProductService productService;
    private SKUProductFactory skuProductFactory;

    @Mappings({
            @Mapping(target = "cart", ignore = true),
            @Mapping(target = "skuProduct", ignore = true)
    })
    public abstract CommerceCartParameter convertToCommerceCartParameter(AddToCartDTO addToCartDTO);

    @AfterMapping
    protected void after(AddToCartDTO addToCartDTO, @MappingTarget CommerceCartParameter commerceCartParameter)
    {
        ProductEntity productEntity = getProductService().getProductForId(addToCartDTO.getProductId());

        ProductVariantEntity variantEntity = null;
        if (addToCartDTO.getVariantId() != null)
        {
            variantEntity = productService.getVariantForProduct(productEntity, addToCartDTO.getVariantId());
        }

        SKUProduct skuProduct = getSkuProductFactory().createSKUProduct(productEntity, variantEntity);
        CartEntity cart = getCartService().getCartForCurrentCustomer();

        commerceCartParameter.setCart(cart);
        commerceCartParameter.setSkuProduct(skuProduct);
        commerceCartParameter.setQuantity(addToCartDTO.getQuantity());
    }


    public CartService getCartService()
    {
        return cartService;
    }

    @Autowired
    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public ProductService getProductService()
    {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService)
    {
        this.productService = productService;
    }

    public SKUProductFactory getSkuProductFactory()
    {
        return skuProductFactory;
    }

    @Autowired
    public void setSkuProductFactory(SKUProductFactory skuProductFactory)
    {
        this.skuProductFactory = skuProductFactory;
    }
}
