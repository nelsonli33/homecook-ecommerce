package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.VoucherUseType;
import com.homecook.homecookentity.type.VoucherValueType;

import javax.persistence.*;
import java.util.*;

@Table(name = EntityConstant.Table.Voucher)
@Entity
public class VoucherEntity extends AbstractBaseEntity
{
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @Column(name = "use_type")
    private VoucherUseType useType;

    @Column(name = "value_type")
    private VoucherValueType valueType;

    @Column(name = "usage_count")
    private Integer usageCount;

    @Column(name = "receive_count")
    private Integer receiveCount;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "max_discount_value")
    private Double maxDiscountValue;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "value")
    private Double value;

    @Column(name = "minimum_amount")
    private Double minimumAmount;

    @Column(name = "current_usage_count")
    private Integer currentUsageCount;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "display")
    private Boolean display;

    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoucherReceiveEntity> voucherReceives = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = EntityConstant.Table.Voucher2CategoryRestrictions,
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> appliedCategories = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = EntityConstant.Table.Voucher2ProductRestrictions,
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<ProductEntity> appliedProducts = new HashSet<>();


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

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public VoucherUseType getUseType()
    {
        return useType;
    }

    public void setUseType(VoucherUseType useType)
    {
        this.useType = useType;
    }

    public VoucherValueType getValueType()
    {
        return valueType;
    }

    public void setValueType(VoucherValueType valueType)
    {
        this.valueType = valueType;
    }

    public Integer getUsageCount()
    {
        return usageCount;
    }

    public void setUsageCount(Integer usageCount)
    {
        this.usageCount = usageCount;
    }

    public Integer getReceiveCount()
    {
        return receiveCount;
    }

    public void setReceiveCount(Integer receiveCount)
    {
        this.receiveCount = receiveCount;
    }

    public Integer getUsageLimit()
    {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit)
    {
        this.usageLimit = usageLimit;
    }

    public Double getMaxDiscountValue()
    {
        return maxDiscountValue;
    }

    public void setMaxDiscountValue(Double maxDiscountValue)
    {
        this.maxDiscountValue = maxDiscountValue;
    }

    public Double getPercentage()
    {
        return percentage;
    }

    public void setPercentage(Double percentage)
    {
        this.percentage = percentage;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
    }

    public Double getMinimumAmount()
    {
        return minimumAmount;
    }

    public void setMinimumAmount(Double minimumAmount)
    {
        this.minimumAmount = minimumAmount;
    }

    public Integer getCurrentUsageCount()
    {
        return currentUsageCount;
    }

    public void setCurrentUsageCount(Integer currentUsageCount)
    {
        this.currentUsageCount = currentUsageCount;
    }

    public Date getStartAt()
    {
        return startAt;
    }

    public void setStartAt(Date startAt)
    {
        this.startAt = startAt;
    }

    public Date getEndAt()
    {
        return endAt;
    }

    public void setEndAt(Date endAt)
    {
        this.endAt = endAt;
    }

    public Boolean getDisplay()
    {
        return display;
    }

    public void setDisplay(Boolean display)
    {
        this.display = display;
    }

    public List<VoucherReceiveEntity> getVoucherReceives()
    {
        return voucherReceives;
    }

    public void setVoucherReceives(List<VoucherReceiveEntity> voucherReceives)
    {
        this.voucherReceives = voucherReceives;
    }

    public Set<CategoryEntity> getAppliedCategories()
    {
        return appliedCategories;
    }

    public void setAppliedCategories(Set<CategoryEntity> appliedCategories)
    {
        this.appliedCategories = appliedCategories;
    }

    public Set<ProductEntity> getAppliedProducts()
    {
        return appliedProducts;
    }

    public void setAppliedProducts(Set<ProductEntity> appliedProducts)
    {
        this.appliedProducts = appliedProducts;
    }


}
