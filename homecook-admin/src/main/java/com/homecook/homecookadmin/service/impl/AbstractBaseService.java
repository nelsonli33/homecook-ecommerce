package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookentity.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseService
{
    private ModelService modelService;

    public ModelService getModelService()
    {
        return modelService;
    }

    @Autowired
    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
