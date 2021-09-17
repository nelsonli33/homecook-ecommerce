package com.homecook.homecookadmin.facade.converter.populator;

import com.homecook.homecookadmin.dto.ProductImageData;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookdomain.model.ProductImageModel;
import org.springframework.util.Assert;

public class ProductImagePopulator implements Populator<ProductImageModel, ProductImageData>
{
    @Override
    public void populate(ProductImageModel source, ProductImageData target)
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
