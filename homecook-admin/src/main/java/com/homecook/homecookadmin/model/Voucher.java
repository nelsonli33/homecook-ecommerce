package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Voucher
{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("note")
    private String note;

    @JsonProperty("valueType")
    private int valueType;

    @JsonProperty("minimumAmount")
    private int minimumAmount;

    @JsonProperty("useType")
    private int useType;

    @JsonProperty("startAt")
    private String startAt;

    @JsonProperty("endAt")
    private String endAt;

    @JsonProperty("value")
    private Double value;

    @JsonProperty("usageCount")
    private int usageCount;

    @JsonProperty("status")
    private int status;

    @JsonProperty("maxDiscountValue")
    private Double maxDiscountValue;

    @JsonProperty("percentage")
    private Double percentage;

    @JsonProperty("display")
    private Boolean display;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("appliedCategories")
    private List<Category> appliedCategories;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("appliedProducts")
    private List<Product> appliedProducts;

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

    public int getValueType()
    {
        return valueType;
    }

    public void setValueType(int valueType)
    {
        this.valueType = valueType;
    }

    public int getMinimumAmount()
    {
        return minimumAmount;
    }

    public void setMinimumAmount(int minimumAmount)
    {
        this.minimumAmount = minimumAmount;
    }

    public int getUseType()
    {
        return useType;
    }

    public void setUseType(int useType)
    {
        this.useType = useType;
    }

    public String getStartAt()
    {
        return startAt;
    }

    public void setStartAt(String startAt)
    {
        this.startAt = startAt;
    }

    public String getEndAt()
    {
        return endAt;
    }

    public void setEndAt(String endAt)
    {
        this.endAt = endAt;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue(Double value)
    {
        this.value = value;
    }

    public int getUsageCount()
    {
        return usageCount;
    }

    public void setUsageCount(int usageCount)
    {
        this.usageCount = usageCount;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
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

    public Boolean getDisplay()
    {
        return display;
    }

    public void setDisplay(Boolean display)
    {
        this.display = display;
    }

    public List<Category> getAppliedCategories()
    {
        return appliedCategories;
    }

    public void setAppliedCategories(List<Category> appliedCategories)
    {
        this.appliedCategories = appliedCategories;
    }

    public List<Product> getAppliedProducts()
    {
        return appliedProducts;
    }

    public void setAppliedProducts(List<Product> appliedProducts)
    {
        this.appliedProducts = appliedProducts;
    }
}
