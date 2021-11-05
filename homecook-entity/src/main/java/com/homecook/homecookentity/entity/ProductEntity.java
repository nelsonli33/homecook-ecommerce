package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.ProductStatusType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = EntityConstant.Table.Product)
@Entity
@DynamicUpdate
public class ProductEntity extends AbstractBaseEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "summary")
    private String summary;

    @Column(length = 65535, columnDefinition = "TEXT")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double price;

    @Column(name = "sales_unit", nullable = false)
    private String salesUnit;

    @Column(name = "sku")
    private String sku;

    @Column(name = "days_to_ship")
    private Integer daysToShip;

    @Column(name = "status")
    @ColumnDefault("0")
    private ProductStatusType status; // 商品上下架狀態：0下架, 1上架, 2已删除

    @Column(name = "max_order_quantity")
    private Integer maxOrderQuantity;

    @Column(name = "min_order_quantity")
    private Integer minOrderQuantity;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_description", length = 1000, columnDefinition = "TEXT")
    private String metaDescription;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = EntityConstant.Table.Product2Category,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = EntityConstant.Table.Product2ShippingMode,
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "shipping_mode_id"))
    private Set<ShippingModeEntity> shippingModes = new HashSet<>();


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductSpecAttributeEntity> specs = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> variants = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @OrderBy("sortOrder ASC")
    private List<ProductImageEntity> images = new ArrayList<>();


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

    public String getSalesUnit()
    {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit)
    {
        this.salesUnit = salesUnit;
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

    public Set<ShippingModeEntity> getShippingModes()
    {
        return shippingModes;
    }

    public void setShippingModes(Set<ShippingModeEntity> shippingModes)
    {
        this.shippingModes = shippingModes;
    }

    public List<ProductSpecAttributeEntity> getSpecs()
    {
        return specs;
    }

    public void setSpecs(List<ProductSpecAttributeEntity> specs)
    {
        this.specs = specs;
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