package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.ProductStatusType;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = EntityConstant.Table.Product)
@Entity
@DynamicUpdate
public class ProductEntity extends AbstractBaseEntity
{

    private String name;
    private String summary;
    @Column(length = 65535, columnDefinition = "TEXT")
    private String description;
    private Integer quantity;
    private Double price;
    private String sku;
    private Integer daysToShip;
    private ProductStatusType status; // 商品上下架狀態：0下架, 1上架, 2已删除
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
    private Set<CategoryEntity> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttributeEntity> attributes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageEntity> images;

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

    public ProductStatusType getStatus()
    {
        return status;
    }

    public void setStatus(ProductStatusType status)
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

    public Set<CategoryEntity> getCategories()
    {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories)
    {
        this.categories = categories;
    }

    public List<ProductAttributeEntity> getAttributes()
    {
        return attributes;
    }

    public void setAttributes(List<ProductAttributeEntity> attributes)
    {
        this.attributes = attributes;
    }

    public List<ProductVariantEntity> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantEntity> variants)
    {
        this.variants = variants;
    }

    public List<ProductImageEntity> getImages()
    {
        return images;
    }

    public void setImages(List<ProductImageEntity> images)
    {
        this.images = images;
    }
}