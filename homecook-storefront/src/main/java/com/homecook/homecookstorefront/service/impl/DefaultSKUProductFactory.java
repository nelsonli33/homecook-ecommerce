package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.service.SKUProductFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "skuProductFactory")
public class DefaultSKUProductFactory implements SKUProductFactory
{
    @Override
    public SKUProduct createSKUProduct(ProductEntity product, ProductVariantEntity variant)
    {
        validateParameterNotNullStandardMessage("product", product);

        SKUProduct skuProduct = new SKUProduct();
        Long productId = product.getId();
        String name = product.getName();
        Double price = product.getPrice();
        Integer stock = product.getQuantity();
        String sku = product.getSku();
        boolean hasVariant = false;
        Long variantId = null;

        if (variant != null)
        {
            name = name.concat(" - ").concat(getVariantName(variant));
            price = variant.getPrice();
            stock = variant.getQuantity();
            sku = variant.getSku();
            variantId = variant.getId();
            hasVariant = true;
        }


        skuProduct.setName(name);
        skuProduct.setStock(stock);
        skuProduct.setPrice(price);
        skuProduct.setProduct(product);
        skuProduct.setVariant(variant);
        skuProduct.setSku(sku);
        skuProduct.setHasVariant(hasVariant);
        skuProduct.setKey(generateKey(productId, variantId, name, price, sku));
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

    private String generateKey(Long productId, Long variantId, String name, Double price, String sku)
    {
        StringBuilder builder = new StringBuilder();
        builder.append(productId).append(":");
        builder.append(variantId == null ? "" : variantId).append(":");

        StringBuilder propertyBuilder = new StringBuilder();
        propertyBuilder.append(name);
        propertyBuilder.append(price);
        propertyBuilder.append(sku);
        String key = DigestUtils.md5DigestAsHex(propertyBuilder.toString().getBytes(StandardCharsets.UTF_8));

        builder.append(key);
        return builder.toString();
    }
}
