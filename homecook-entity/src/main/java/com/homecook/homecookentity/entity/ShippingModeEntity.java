package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.ShippingModeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Table(name = EntityConstant.Table.ShippingMode)
@Entity
public class ShippingModeEntity extends AbstractBaseEntity
{
    @Column(name = "code", nullable = false, unique = true, insertable = false, updatable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "shipping_cost")
    private Double shippingCost;

    @Column(name = "mode_type")
    private ShippingModeType shippingModeType;

    @ManyToMany(mappedBy = "shippingModes")
    private Set<ProductEntity> products = new HashSet<>();

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public Double getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost)
    {
        this.shippingCost = shippingCost;
    }

    public ShippingModeType getShippingModeType()
    {
        return shippingModeType;
    }

    public void setShippingModeType(ShippingModeType shippingModeType)
    {
        this.shippingModeType = shippingModeType;
    }

    public Set<ProductEntity> getProducts()
    {
        return products;
    }

    public void setProducts(Set<ProductEntity> products)
    {
        this.products = products;
    }
}
