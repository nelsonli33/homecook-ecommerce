package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = EntityConstant.Table.ProductVariant)
@Entity
@DynamicUpdate
public class ProductVariantEntity extends AbstractBaseEntity
{
    @Column(columnDefinition = "DECIMAL(10,2)")
    private Double price;
    private Integer quantity;
    private String sku;
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductImageEntity image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_value1_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductAttributeValueEntity specValue1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_value2_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductAttributeValueEntity specValue2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_value3_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductAttributeValueEntity specValue3;



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

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public ProductAttributeValueEntity getSpecValue1()
    {
        return specValue1;
    }

    public void setSpecValue1(ProductAttributeValueEntity specValue1)
    {
        this.specValue1 = specValue1;
    }

    public ProductAttributeValueEntity getSpecValue2()
    {
        return specValue2;
    }

    public void setSpecValue2(ProductAttributeValueEntity specValue2)
    {
        this.specValue2 = specValue2;
    }

    public ProductAttributeValueEntity getSpecValue3()
    {
        return specValue3;
    }

    public void setSpecValue3(ProductAttributeValueEntity specValue3)
    {
        this.specValue3 = specValue3;
    }

    public ProductEntity getProduct()
    {
        return product;
    }

    public void setProduct(ProductEntity product)
    {
        this.product = product;
    }

    public ProductImageEntity getImage()
    {
        return image;
    }

    public void setImage(ProductImageEntity image)
    {
        this.image = image;
    }
}