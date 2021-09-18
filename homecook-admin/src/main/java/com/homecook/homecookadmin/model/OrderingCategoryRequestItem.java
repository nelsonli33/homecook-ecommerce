package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderingCategoryRequestItem{

	@JsonProperty("sortOrder")
	private Integer sortOrder;

	@JsonProperty("id")
	private Integer id;

	public void setSortOrder(Integer sortOrder){
		this.sortOrder = sortOrder;
	}

	public Integer getSortOrder(){
		return sortOrder;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
}