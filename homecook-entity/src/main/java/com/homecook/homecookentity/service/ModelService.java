package com.homecook.homecookentity.service;

import com.homecook.homecookentity.entity.AbstractBaseEntity;

import java.util.Collection;

public interface ModelService
{
    void save(AbstractBaseEntity model);

    void refresh(AbstractBaseEntity model);

    void saveAll(Collection<? extends AbstractBaseEntity> models);

    void remove(AbstractBaseEntity model);

    void removeAll(Iterable<? extends AbstractBaseEntity> models);
}
