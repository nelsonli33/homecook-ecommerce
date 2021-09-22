package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, Long>
{
}
