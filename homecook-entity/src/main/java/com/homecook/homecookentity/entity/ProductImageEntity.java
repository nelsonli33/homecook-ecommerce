package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;
import java.util.List;


@Table(name = EntityConstant.Table.ProductImage, indexes = {
        @Index(name = "idx_productimagemodel_code", columnList = "code")
})
@Entity
public class ProductImageEntity extends AbstractBaseEntity
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
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductEntity product;

    @OneToMany(mappedBy = "image")
    private List<ProductVariantEntity> variants;

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

    public ProductEntity getProduct()
    {
        return product;
    }

    public void setProduct(ProductEntity product)
    {
        this.product = product;
    }

    public List<ProductVariantEntity> getVariants()
    {
        return variants;
    }

    public void setVariants(List<ProductVariantEntity> variants)
    {
        this.variants = variants;
    }
}