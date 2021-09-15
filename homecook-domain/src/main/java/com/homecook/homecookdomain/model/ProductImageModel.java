package com.homecook.homecookdomain.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "productimages")
@Entity
public class ProductImageModel extends AbstractBaseModel
{
    private String filename;
    private String originfilename;
    private Integer position;
    private String tinyUrl;
    private String thumbnailUrl;
    private String normalUrl;
    private String detailUrl;
    private String zoomUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @OneToMany(mappedBy = "image")
    private List<ProductVariantModel> variants;


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

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    public String getTinyUrl()
    {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl)
    {
        this.tinyUrl = tinyUrl;
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

    public ProductModel getProduct()
    {
        return product;
    }

    public void setProduct(ProductModel product)
    {
        this.product = product;
    }

    public List<ProductVariantModel> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantModel> variants)
    {
        this.variants = variants;
    }
}