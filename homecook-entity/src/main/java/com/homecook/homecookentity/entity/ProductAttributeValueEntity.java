package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;

@Entity
@Table(name = EntityConstant.Table.ProductAttributeValue)
public class ProductAttributeValueEntity extends AbstractBaseEntity
{
    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductAttributeEntity attribute;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public ProductAttributeEntity getAttribute()
    {
        return attribute;
    }

    public void setAttribute(ProductAttributeEntity attribute)
    {
        this.attribute = attribute;
    }
}
