package com.homecook.homecookadmin.dto;

import java.util.List;

public class ProductSpecDTO
{
    private Long id;
    private String name;
    private List<ProductSpecValueDTO> values;

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

    public List<ProductSpecValueDTO> getValues()
    {
        return values;
    }

    public void setValues(List<ProductSpecValueDTO> values)
    {
        this.values = values;
    }
}
