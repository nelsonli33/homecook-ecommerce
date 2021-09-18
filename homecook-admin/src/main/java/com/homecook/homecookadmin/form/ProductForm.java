package com.homecook.homecookadmin.form;

import java.util.List;

public class ProductForm
{
    private List<Long> categoryIds;
    private String name;
    private String summary;
    private String description;
    private Double price;
    private Integer quantity;
    private String sku;
    private Integer daysToShip;
    private Integer status;
    private Integer minOrderQuantity;
    private Integer maxOrderQuantity;
    private String metaTitle;
    private String metaDescription;
    private List<ProductSpecAttributeForm> specs;
    private List<ProductVariantForm> variants;
    private List<ProductImageForm> images;

    public List<Long> getCategoryIds()
    {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds)
    {
        this.categoryIds = categoryIds;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku(String sku)
    {
        this.sku = sku;
    }

    public Integer getDaysToShip()
    {
        return daysToShip;
    }

    public void setDaysToShip(Integer daysToShip)
    {
        this.daysToShip = daysToShip;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getMinOrderQuantity()
    {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(Integer minOrderQuantity)
    {
        this.minOrderQuantity = minOrderQuantity;
    }

    public Integer getMaxOrderQuantity()
    {
        return maxOrderQuantity;
    }

    public void setMaxOrderQuantity(Integer maxOrderQuantity)
    {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    public String getMetaTitle()
    {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle)
    {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription()
    {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription)
    {
        this.metaDescription = metaDescription;
    }

    public List<ProductSpecAttributeForm> getSpecs()
    {
        return specs;
    }

    public void setSpecs(List<ProductSpecAttributeForm> specs)
    {
        this.specs = specs;
    }

    public List<ProductVariantForm> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantForm> variants)
    {
        this.variants = variants;
    }

    public List<ProductImageForm> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageForm> images)
    {
        this.images = images;
    }
}
