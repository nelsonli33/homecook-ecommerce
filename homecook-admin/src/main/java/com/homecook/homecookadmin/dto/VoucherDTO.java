package com.homecook.homecookadmin.dto;

import com.homecook.homecookentity.type.VoucherStatusType;
import com.homecook.homecookentity.type.VoucherUseType;
import com.homecook.homecookentity.type.VoucherValueType;

import java.util.Date;
import java.util.List;

public class VoucherDTO
{
    private Long id;
    private String code;
    private String name;
    private String note;
    private VoucherUseType useType;
    private VoucherValueType valueType;
    private int usageCount;
    private Double value;
    private Double minimumAmount;
    private Double maxDiscountValue;
    private Double percentage;
    private Date startAt;
    private Date endAt;
    private Boolean display;
    private VoucherStatusType status;
    private List<Long> appliedCategoryIds;
    private List<Long> appliedProductIds;
    private List<CategoryDTO> appliedCategories;
    private List<ProductDTO> appliedProducts;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

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

    public int getUsageCount()
    {
        return usageCount;
    }

    public void setUsageCount(int usageCount)
    {
        this.usageCount = usageCount;
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

    public VoucherStatusType getStatus()
    {
        return status;
    }

    public void setStatus(VoucherStatusType status)
    {
        this.status = status;
    }

    public List<Long> getAppliedCategoryIds()
    {
        return appliedCategoryIds;
    }

    public void setAppliedCategoryIds(List<Long> appliedCategoryIds)
    {
        this.appliedCategoryIds = appliedCategoryIds;
    }

    public List<Long> getAppliedProductIds()
    {
        return appliedProductIds;
    }

    public void setAppliedProductIds(List<Long> appliedProductIds)
    {
        this.appliedProductIds = appliedProductIds;
    }

    public List<CategoryDTO> getAppliedCategories()
    {
        return appliedCategories;
    }

    public void setAppliedCategories(List<CategoryDTO> appliedCategories)
    {
        this.appliedCategories = appliedCategories;
    }

    public List<ProductDTO> getAppliedProducts()
    {
        return appliedProducts;
    }

    public void setAppliedProducts(List<ProductDTO> appliedProducts)
    {
        this.appliedProducts = appliedProducts;
    }
}
