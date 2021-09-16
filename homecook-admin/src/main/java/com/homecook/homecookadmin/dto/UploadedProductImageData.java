package com.homecook.homecookadmin.dto;

public class UploadedProductImageData
{
    private String filename;
    private String thumbnail;
    private String normal;
    private String detail;
    private String zoom;

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

    public String getZoom()
    {
        return zoom;
    }

    public void setZoom(String zoom)
    {
        this.zoom = zoom;
    }
}
