package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category{

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("sortOrder")
	private Integer sortOrder;

	@JsonProperty("parentId")
	private Long parentId;

	@JsonProperty("metaTitle")
	private String metaTitle;

	@JsonProperty("metaDescription")
	private String metaDescription;


	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

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

	public Long getParentId()
	{
		return parentId;
	}

	public void setParentId(Long parentId)
	{
		this.parentId = parentId;
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

}