package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = EntityConstant.Table.ProductAttributeCategory)
public class ProductAttributeCategoryEntity extends AbstractBaseEntity
{
    private String name;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "attributeCategory")
    private CategoryEntity category;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public CategoryEntity getCategory()
    {
        return category;
    }

    public void setCategory(CategoryEntity category)
    {
        this.category = category;
    }

}
