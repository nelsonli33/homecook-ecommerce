package com.homecook.homecookadmin.dto;

public class ProductImageDTO
{
    private String code;
    private String filename;
    private String originfilename;
    private String thumbnail;
    private String normal;
    private String detail;
    private String zoom;
    private Integer position;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getOriginfilename()
    {
        return originfilename;
    }

    public void setOriginfilename(String originfilename)
    {
        this.originfilename = originfilename;
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

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }
}
