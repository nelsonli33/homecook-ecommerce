package com.homecook.homecookstorefront.service.impl;


import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookentity.repository.ProductRepository;
import com.homecook.homecookstorefront.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "stockService")
public class DefaultStockService implements StockService
{

    private ProductRepository productRepository;

    @Autowired
    public DefaultStockService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public boolean reserveStock(ProductVariantEntity productVariant, int quantity)
    {
        validateParameterNotNullStandardMessage("productVariant", productVariant);

        int availableQuantity = getAvailableToSellQuantity(productVariant);

        if (availableQuantity >= quantity)
        {
            final long rowUpdated = productRepository.reserveStockByProductVariant(productVariant.getId(), quantity);

            if (rowUpdated == 1)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean reduceStock(ProductVariantEntity productVariant, int quantity)
    {
        validateParameterNotNullStandardMessage("productVariant", productVariant);

        final long rowUpdated = productRepository.reduceStockByProductVariant(productVariant.getId(), quantity);
        if (rowUpdated == 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public Integer getAvailableToSellQuantity(ProductVariantEntity productVariant)
    {
        validateParameterNotNullStandardMessage("productVariant", productVariant);

        if (productVariant.getQuantity() == null)
        {
            return null;
        }

        return productVariant.getQuantity() - productVariant.getReserveStock();
    }
}
