package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;
import java.util.List;


@Table(name = EntityConstant.Table.ProductImage)
@Entity
public class ProductImageEntity extends AbstractBaseEntity
{
    @Column(nullable = false, unique = true)
    private String filename;

    @Column(name = "originfilename")
    private String originfilename;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "normal")
    private String normal;

    @Column(name = "detail")
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private ProductEntity product;

    @OneToMany(mappedBy = "image")
    private List<ProductVariantEntity> variants;

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

    public Integer getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder)
    {
        this.sortOrder = sortOrder;
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