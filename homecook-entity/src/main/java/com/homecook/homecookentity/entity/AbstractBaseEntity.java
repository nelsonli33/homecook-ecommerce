package com.homecook.homecookentity.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@DynamicUpdate
@MappedSuperclass
public class AbstractBaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "created_at", columnDefinition = "DATETIME", updatable = false, nullable = false)
    @CreationTimestamp
    protected Date createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    @UpdateTimestamp
    protected Date updatedAt;

    @Column
    @Version
    protected int version;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof AbstractBaseEntity)) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }
}