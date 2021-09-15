package com.homecook.homecookdomain.repository;

import com.homecook.homecookdomain.model.AbstractBaseModel;

public interface ModelRepository<T extends AbstractBaseModel>
        extends CustomJpaRepository<T, Long>
{
}
