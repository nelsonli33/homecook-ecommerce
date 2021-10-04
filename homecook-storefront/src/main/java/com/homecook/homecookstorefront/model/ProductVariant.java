package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariant
{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("specValue1Id")
    private Long specValue1Id;

    @JsonProperty("specValue2Id")
    private Long specValue2Id;

    @JsonProperty("specValue3Id")
    private Long specValue3Id;

    @JsonProperty("specValue1")
    private String specValue1;

    @JsonProperty("specValue2")
    private String specValue2;

    @JsonProperty("specValue3")
    private String specValue3;


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

    public Long getSpecValue1Id()
    {
        return specValue1Id;
    }

    public void setSpecValue1Id(Long specValue1Id)
    {
        this.specValue1Id = specValue1Id;
    }

    public Long getSpecValue2Id()
    {
        return specValue2Id;
    }

    public void setSpecValue2Id(Long specValue2Id)
    {
        this.specValue2Id = specValue2Id;
    }

    public Long getSpecValue3Id()
    {
        return specValue3Id;
    }

    public void setSpecValue3Id(Long specValue3Id)
    {
        this.specValue3Id = specValue3Id;
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

    public String getSpecValue3()
    {
        return specValue3;
    }

    public void setSpecValue3(String specValue3)
    {
        this.specValue3 = specValue3;
    }
}