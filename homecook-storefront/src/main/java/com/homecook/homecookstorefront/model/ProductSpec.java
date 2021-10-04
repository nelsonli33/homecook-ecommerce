package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSpec
{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("values")
    private List<ProductSpecValue> values;


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

    public List<ProductSpecValue> getValues()
    {
        return values;
    }

    public void setValues(List<ProductSpecValue> values)
    {
        this.values = values;
    }
}