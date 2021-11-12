package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateVoucherRequest{

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;

	@JsonProperty("note")
	private String note;

	@JsonProperty("valueType")
	private int valueType;

	@JsonProperty("minimumAmount")
	private Double minimumAmount;

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

	@JsonProperty("maxDiscountValue")
	private Double maxDiscountValue;

	@JsonProperty("percentage")
	private Double percentage;

	@JsonProperty("display")
	private Boolean display;

	@JsonProperty("appliedCategoryIds")
	private List<Long> appliedCategoryIds;

	@JsonProperty("appliedProductIds")
	private List<Long> appliedProductIds;

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

	public Double getMinimumAmount()
	{
		return minimumAmount;
	}

	public void setMinimumAmount(Double minimumAmount)
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
}