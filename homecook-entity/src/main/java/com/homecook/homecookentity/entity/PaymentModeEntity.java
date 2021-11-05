package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = EntityConstant.Table.PaymentMode)
@Entity
public class PaymentModeEntity extends AbstractBaseEntity
{
    @Column(name = "code", nullable = false, unique = true, insertable = false, updatable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "description")
    private String description;

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

    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
