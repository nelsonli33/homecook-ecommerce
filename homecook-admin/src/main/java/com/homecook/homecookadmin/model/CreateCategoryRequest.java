package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCategoryRequest
{
	@JsonProperty("sortOrder")
	private Integer sortOrder;

	@JsonProperty("metaTitle")
	private String metaTitle;

	@JsonProperty("name")
	private String name;

	@JsonProperty("metaDescription")
	private String metaDescription;

	@JsonProperty("parentId")
	private Long parentId;

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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
	}
}