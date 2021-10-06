package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.service.SKUProductFactory;
import org.springframework.stereotype.Component;

@Component(value = "skuProductFactory")
public class DefaultSKUProductFactory implements SKUProductFactory
{
    @Override
    public SKUProduct createSKUProduct(ProductEntity product, ProductVariantEntity variant)
    {
        SKUProduct skuProduct = new SKUProduct();
        String name = product.getName();
        Double price = product.getPrice();
        Integer stock = product.getQuantity();
        String sku = product.getSku();
        boolean hasVariant = false;

        if (variant != null)
        {
            name = name.concat(" - ").concat(getVariantName(variant));
            price = variant.getPrice();
            stock = variant.getQuantity();
            sku = variant.getSku();
            hasVariant = true;
        }

        skuProduct.setName(name);
        skuProduct.setStock(stock);
        skuProduct.setPrice(price);
        skuProduct.setProduct(product);
        skuProduct.setVariant(variant);
        skuProduct.setSku(sku);
        skuProduct.setHasVariant(hasVariant);
        return skuProduct;
    }

    private String getVariantName(ProductVariantEntity variant)
    {
        StringBuilder sb = new StringBuilder();
        if (variant.getSpecValue1() != null) sb.append(variant.getSpecValue1().getValue());
        if (variant.getSpecValue2() != null) sb.append(" / ").append(variant.getSpecValue2().getValue());
        if (variant.getSpecValue3() != null) sb.append(" / ").append(variant.getSpecValue3().getValue());
        return sb.toString();
    }
}
