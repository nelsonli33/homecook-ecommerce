package com.homecook.homecookdomain.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(name = "productvariants")
@Entity
@DynamicUpdate
public class ProductVariantModel extends AbstractBaseModel
{
    private String name;
    private Double price;
    private Integer quantity;
    private String sku;
    private String option1;
    private String option2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ProductImageModel image;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getOption1()
    {
        return option1;
    }

    public void setOption1(String option1)
    {
        this.option1 = option1;
    }

    public String getOption2()
    {
        return option2;
    }

    public void setOption2(String option2)
    {
        this.option2 = option2;
    }

    public ProductModel getProduct()
    {
        return product;
    }

    public void setProduct(ProductModel product)
    {
        this.product = product;
    }

    public ProductImageModel getImage()
    {
        return image;
    }

    public void setImage(ProductImageModel image)
    {
        this.image = image;
    }
}