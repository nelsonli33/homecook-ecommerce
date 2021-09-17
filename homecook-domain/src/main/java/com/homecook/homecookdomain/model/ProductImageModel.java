package com.homecook.homecookdomain.model;

import javax.persistence.*;
import java.util.List;


@Table(name = "ProductImageModel", indexes = {
        @Index(name = "idx_productimagemodel_code", columnList = "code")
})
@Entity
public class ProductImageModel extends AbstractBaseModel
{
    @Column(nullable = false, unique = true)
    private String code;
    private String filename;
    private String originfilename;
    private Integer position;
    private String thumbnail;
    private String normal;
    private String detail;
    private String zoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @OneToMany(mappedBy = "image")
    private List<ProductVariantModel> variants;

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

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
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