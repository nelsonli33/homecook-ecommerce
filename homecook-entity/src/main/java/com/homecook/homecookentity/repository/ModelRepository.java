package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.AbstractBaseEntity;

public interface ModelRepository<T extends AbstractBaseEntity>
        extends CustomJpaRepository<T, Long>
{
}
