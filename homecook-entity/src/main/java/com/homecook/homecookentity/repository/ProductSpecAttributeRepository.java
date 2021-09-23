package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.ProductSpecAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpecAttributeRepository extends JpaRepository<ProductSpecAttributeEntity, Long>
{
}
