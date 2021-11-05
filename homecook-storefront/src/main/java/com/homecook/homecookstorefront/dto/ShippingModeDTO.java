package com.homecook.homecookstorefront.dto;

import java.math.BigDecimal;

public class ShippingModeDTO
{
    private Long id;
    private String code;
    private String name;
    private BigDecimal shippingCost;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

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

    public BigDecimal getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost)
    {
        this.shippingCost = shippingCost;
    }
}
