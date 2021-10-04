package com.homecook.homecookstorefront.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO
{
    private Long id;
    private String name;
    private String summary;
    private String description;
    private Integer status;
    private Integer quantity;
    private BigDecimal price;
    private String sku;
    private Integer daysToShip;
    private Integer maxOrderQuantity;
    private Integer minOrderQuantity;
    private String metaTitle;
    private String metaDescription;
    private List<CategoryDTO> categories;
    private List<ProductImageDTO> images;
    private List<ProductSpecDTO> specs;
    private List<ProductVariantDTO> variants;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
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

    public Integer getMaxOrderQuantity()
    {
        return maxOrderQuantity;
    }

    public void setMaxOrderQuantity(Integer maxOrderQuantity)
    {
        this.maxOrderQuantity = maxOrderQuantity;
    }

    public Integer getMinOrderQuantity()
    {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(Integer minOrderQuantity)
    {
        this.minOrderQuantity = minOrderQuantity;
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

    public List<ProductImageDTO> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageDTO> images)
    {
        this.images = images;
    }

    public List<CategoryDTO> getCategories()
    {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories)
    {
        this.categories = categories;
    }

    public List<ProductSpecDTO> getSpecs()
    {
        return specs;
    }

    public void setSpecs(List<ProductSpecDTO> specs)
    {
        this.specs = specs;
    }

    public List<ProductVariantDTO> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantDTO> variants)
    {
        this.variants = variants;
    }
}
