package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;

@Entity
@Table(name = EntityConstant.Table.ProductAttributeValue)
public class ProductAttributeValueEntity extends AbstractBaseEntity
{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    private ProductAttributeEntity attribute;

    private String value;

    public ProductAttributeEntity getAttribute()
    {
        return attribute;
    }

    public void setAttribute(ProductAttributeEntity attribute)
    {
        this.attribute = attribute;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
