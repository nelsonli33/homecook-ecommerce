package com.homecook.homecookdomain.service;

import com.homecook.homecookdomain.model.AbstractBaseModel;

import java.util.Collection;
import java.util.List;

public interface ModelService
{
    void save(AbstractBaseModel model);

    void saveAll(Collection<? extends AbstractBaseModel> models);

    void remove(AbstractBaseModel model);
}
