package com.homecook.homecookstorefront.dto;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;

public class SKUProduct
{
    private String name;
    private Integer stock;
    private Double price;
    private String key;
    private ProductEntity product;
    private ProductVariantEntity variant;
    private String sku;
    private Boolean hasVariant;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getStock()
    {
        return stock;
    }

    public void setStock(Integer stock)
    {
        this.stock = stock;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public ProductEntity getProduct()
    {
        return product;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public void setProduct(ProductEntity product)
    {
        this.product = product;
    }

    public ProductVariantEntity getVariant()
    {
        return variant;
    }

    public void setVariant(ProductVariantEntity variant)
    {
        this.variant = variant;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public Boolean getHasVariant()
    {
        return hasVariant;
    }

    public void setHasVariant(Boolean hasVariant)
    {
        this.hasVariant = hasVariant;
    }
}
