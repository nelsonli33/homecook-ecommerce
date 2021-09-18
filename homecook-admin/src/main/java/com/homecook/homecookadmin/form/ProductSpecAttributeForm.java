package com.homecook.homecookadmin.form;

import java.util.List;

public class ProductSpecAttributeForm
{
    public Long id;
    public String name;
    public List<ProductSpecAttributeValueForm> values;

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

    public List<ProductSpecAttributeValueForm> getValues()
    {
        return values;
    }

    public void setValues(List<ProductSpecAttributeValueForm> values)
    {
        this.values = values;
    }
}
