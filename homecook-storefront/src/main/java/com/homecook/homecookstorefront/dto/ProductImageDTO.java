package com.homecook.homecookstorefront.dto;

public class ProductImageDTO
{
    private Long id;
    private String filename;
    private String thumbnail;
    private String normal;
    private String detail;
    private String thumbnailUrl;
    private String normalUrl;
    private String detailUrl;
    private String zoomUrl;
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


    public String getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getNormal()
    {
        return normal;
    }

    public void setNormal(String normal)
    {
        this.normal = normal;
    }

    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
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

    public String getZoomUrl()
    {
        return zoomUrl;
    }

    public void setZoomUrl(String zoomUrl)
    {
        this.zoomUrl = zoomUrl;
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
