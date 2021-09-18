package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = EntityConstant.Table.Category)
@Entity
@DynamicUpdate
public class CategoryEntity extends AbstractBaseEntity
{
    private String name;
    private Integer sortOrder;
    private String metaTitle;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String metaDescription;

    @Column(name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
    private Set<CategoryEntity> children = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_category_id")
    private ProductAttributeCategoryEntity attributeCategory;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getMetaTitle()
    {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle)
    {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription()
    {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription)
    {
        this.metaDescription = metaDescription;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public CategoryEntity getParent()
    {
        return parent;
    }

    public void setParent(CategoryEntity parent)
    {
        this.parent = parent;
    }

    public Set<CategoryEntity> getChildren()
    {
        return children;
    }

    public void setChildren(Set<CategoryEntity> children)
    {
        this.children = children;
    }

    public ProductAttributeCategoryEntity getAttributeCategory()
    {
        return attributeCategory;
    }

    public void setAttributeCategory(ProductAttributeCategoryEntity attributeCategory)
    {
        this.attributeCategory = attributeCategory;
    }


}