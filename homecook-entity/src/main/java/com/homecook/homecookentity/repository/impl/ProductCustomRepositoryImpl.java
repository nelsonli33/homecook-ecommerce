package com.homecook.homecookentity.repository.impl;

import com.homecook.homecookentity.entity.QProductVariantEntity;
import com.homecook.homecookentity.repository.ProductCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public class ProductCustomRepositoryImpl implements ProductCustomRepository
{
    private JPAQueryFactory queryFactory;

    @Autowired
    public ProductCustomRepositoryImpl(JPAQueryFactory queryFactory)
    {
        this.queryFactory = queryFactory;
    }


    @Override
    @Transactional
    @Modifying
    public long reserveStockByProductVariant(Long productVariantId, Integer quantity)
    {
        QProductVariantEntity productVariant = QProductVariantEntity.productVariantEntity;

        return queryFactory.update(productVariant)
                .set(productVariant.reserveStock, productVariant.reserveStock.add(quantity))
                .set(productVariant.version, productVariant.version.add(1))
                .where(productVariant.id.eq(productVariantId).and(productVariant.quantity.goe(0)))
                .execute();
    }


    @Override
    @Transactional
    @Modifying
    public long releaseStockByProductVariant(Long productVariantId, Integer quantity)
    {
        QProductVariantEntity productVariant = QProductVariantEntity.productVariantEntity;

        return queryFactory.update(productVariant)
                .set(productVariant.quantity, productVariant.quantity.add(quantity))
                .set(productVariant.reserveStock, productVariant.reserveStock.subtract(quantity))
                .set(productVariant.version, productVariant.version.add(1))
                .where(productVariant.id.eq(productVariantId).and(productVariant.reserveStock.goe(0)))
                .execute();
    }

    @Override
    @Transactional
    @Modifying
    public long reduceStockByProductVariant(Long productVariantId, Integer quantity)
    {
        QProductVariantEntity productVariant = QProductVariantEntity.productVariantEntity;

        return queryFactory.update(productVariant)
                .set(productVariant.quantity, productVariant.quantity.subtract(quantity))
                .set(productVariant.reserveStock, productVariant.reserveStock.subtract(quantity))
                .set(productVariant.version, productVariant.version.add(1))
                .where(productVariant.id.eq(productVariantId).and(productVariant.quantity.goe(0)).and(productVariant.reserveStock.goe(0)))
                .execute();
    }
}
