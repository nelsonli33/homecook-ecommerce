package com.homecook.homecookadmin.form;

import java.math.BigDecimal;

public class ProductVariantForm
{
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String sku;
    private String specValue1;
    private String specValue2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpecValue1()
    {
        return specValue1;
    }

    public void setSpecValue1(String specValue1)
    {
        this.specValue1 = specValue1;
    }

    public String getSpecValue2()
    {
        return specValue2;
    }

    public void setSpecValue2(String specValue2)
    {
        this.specValue2 = specValue2;
    }
}
