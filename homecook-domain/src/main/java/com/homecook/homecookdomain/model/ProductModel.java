package com.homecook.homecookdomain.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "products")
@Entity
@DynamicUpdate
public class ProductModel extends AbstractBaseModel
{
    private String name;
    private String summary;
    @Column(length = 65535, columnDefinition = "TEXT")
    private String description;
    private Integer quantity;
    private Double price;
    private String sku;
    private Integer daysToShip;
    private Integer status; // 商品上下架狀態：0下架, 1上架, 2已删除
    private Integer maxOrderQuantity;
    private Integer minOrderQuantity;
    private String metaTitle;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String metaDescription;

    @ManyToMany(
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    @JoinTable(name = "product2catrel",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryModel> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOptionModel> options;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantModel> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageModel> images;

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

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
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

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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

    public Set<CategoryModel> getCategories()
    {
        return categories;
    }

    public void setCategories(Set<CategoryModel> categories)
    {
        this.categories = categories;
    }

    public List<ProductOptionModel> getOptions()
    {
        return options;
    }

    public void setOptions(List<ProductOptionModel> options)
    {
        this.options = options;
    }

    public List<ProductVariantModel> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantModel> variants)
    {
        this.variants = variants;
    }

    public List<ProductImageModel> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageModel> images)
    {
        this.images = images;
    }
}