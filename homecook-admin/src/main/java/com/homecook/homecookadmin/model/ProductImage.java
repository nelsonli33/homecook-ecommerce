package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductImage
{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("filename")
    private String filename;

    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;

    @JsonProperty("normalUrl")
    private String normalUrl;

    @JsonProperty("detailUrl")
    private String detailUrl;

    @JsonProperty("sortOrder")
    private Integer sortOrder;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getThumbnailUrl()
    {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl)
    {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getNormalUrl()
    {
        return normalUrl;
    }

    public void setNormalUrl(String normalUrl)
    {
        this.normalUrl = normalUrl;
    }

    public String getDetailUrl()
    {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl)
    {
        this.detailUrl = detailUrl;
    }

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
    }
}
