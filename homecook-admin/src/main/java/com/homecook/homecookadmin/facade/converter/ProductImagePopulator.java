package com.homecook.homecookadmin.facade.converter;

import com.homecook.homecookadmin.dto.ProductImageDTO;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookentity.entity.ProductImageEntity;
import org.springframework.util.Assert;

public class ProductImagePopulator implements Populator<ProductImageEntity, ProductImageDTO>
{
    @Override
    public void populate(ProductImageEntity source, ProductImageDTO target)
    {
        Assert.notNull(source, "Parameter productImageModel cannot be null.");
        Assert.notNull(target, "Parameter productImageData cannot be null.");

        target.setCode(source.getCode());
        target.setFilename(source.getFilename());
        target.setOriginfilename(source.getOriginfilename());
        target.setPosition(source.getPosition());
        target.setZoom(source.getZoom());
        target.setDetail(source.getDetail());
        target.setNormal(source.getNormal());
        target.setThumbnail(source.getThumbnail());
    }
}
