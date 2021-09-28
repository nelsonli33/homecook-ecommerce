package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.homecook.homecookadmin.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductRequest
{
	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonSerialize(using = BigDecimalSerializer.class)
	@JsonProperty("price")
	private BigDecimal price;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("categoryIds")
	private List<Long> categoryIds;

	@JsonProperty("imageIds")
	private List<Long> productImageIds;

	@JsonProperty("minOrderQuantity")
	private Integer minOrderQuantity;

	@JsonProperty("maxOrderQuantity")
	private Integer maxOrderQuantity;

	@JsonProperty("sku")
	private String sku;

	@JsonProperty("daysToShip")
	private Integer daysToShip;

	@JsonProperty("imageIds")
	private List<Long> imageIds;

	@JsonProperty("status")
	private Integer status;

	@JsonProperty("variants")
	private List<ProductVariant> variants;

	@JsonProperty("specs")
	private List<ProductSpec> specs;


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

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public List<Long> getCategoryIds()
	{
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds)
	{
		this.categoryIds = categoryIds;
	}

	public List<Long> getProductImageIds()
	{
		return productImageIds;
	}

	public void setProductImageIds(List<Long> productImageIds)
	{
		this.productImageIds = productImageIds;
	}

	public Integer getMinOrderQuantity()
	{
		return minOrderQuantity;
	}

	public void setMinOrderQuantity(Integer minOrderQuantity)
	{
		this.minOrderQuantity = minOrderQuantity;
	}

	public Integer getMaxOrderQuantity()
	{
		return maxOrderQuantity;
	}

	public void setMaxOrderQuantity(Integer maxOrderQuantity)
	{
		this.maxOrderQuantity = maxOrderQuantity;
	}

	public String getSku()
	{
		return sku;
	}

	public void setSku(String sku)
	{
		this.sku = sku;
	}

	public Integer getDaysToShip()
	{
		return daysToShip;
	}

	public void setDaysToShip(Integer daysToShip)
	{
		this.daysToShip = daysToShip;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<ProductVariant> getVariants()
	{
		return variants;
	}

	public void setVariants(List<ProductVariant> variants)
	{
		this.variants = variants;
	}

	public List<ProductSpec> getSpecs()
	{
		return specs;
	}

	public void setSpecs(List<ProductSpec> specs)
	{
		this.specs = specs;
	}
}