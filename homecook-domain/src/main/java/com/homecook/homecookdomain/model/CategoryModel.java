package com.homecook.homecookdomain.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "categories")
@Entity
@DynamicUpdate
public class CategoryModel extends AbstractBaseModel
{
    private String name;
    private Integer sortOrder; // 排序
    private String metaTitle;
    @Column(length = 1000, columnDefinition = "TEXT")
    private String metaDescription;

    @Column(name = "parent_id", insertable = false, updatable = false)
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryModel parent;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="parent")
    private Set<CategoryModel> children = new HashSet<>();


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

    public CategoryModel getParent()
    {
        return parent;
    }

    public void setParent(CategoryModel parent)
    {
        this.parent = parent;
    }

    public Set<CategoryModel> getChildren()
    {
        return children;
    }

    public void setChildren(Set<CategoryModel> children)
    {
        this.children = children;
    }
}