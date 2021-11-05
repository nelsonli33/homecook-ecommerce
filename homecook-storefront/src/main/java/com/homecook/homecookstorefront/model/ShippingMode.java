package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShippingMode
{

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shippingCost")
    private double shippingCost;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost)
    {
        this.shippingCost = shippingCost;
    }
}