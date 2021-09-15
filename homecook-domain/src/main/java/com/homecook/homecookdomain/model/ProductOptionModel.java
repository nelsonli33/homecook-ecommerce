package com.homecook.homecookdomain.model;

import javax.persistence.*;

@Table(name = "productoptions")
@Entity
public class ProductOptionModel extends AbstractBaseModel
{
    private String name;
    private Integer position;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    public ProductModel getProduct()
    {
        return product;
    }

    public void setProduct(ProductModel product)
    {
        this.product = product;
    }
}