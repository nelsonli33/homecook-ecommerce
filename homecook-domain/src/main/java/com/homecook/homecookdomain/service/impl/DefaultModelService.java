package com.homecook.homecookdomain.service.impl;

import com.homecook.homecookdomain.model.AbstractBaseModel;
import com.homecook.homecookdomain.repository.ModelRepository;
import com.homecook.homecookdomain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service(value = "modelService")
public class DefaultModelService implements ModelService
{
    private ModelRepository<AbstractBaseModel> modelRepository;

    @Autowired
    public DefaultModelService(ModelRepository<AbstractBaseModel> modelRepository)
    {
        this.modelRepository = modelRepository;
    }

    @Override
    public void save(AbstractBaseModel model)
    {
        modelRepository.save(model);
        modelRepository.refresh(model);
    }

    @Override
    public void saveAll(Collection<? extends AbstractBaseModel> models)
    {
        modelRepository.saveAll(models);
    }


    @Override
    public void remove(AbstractBaseModel model)
    {
        modelRepository.delete(model);
    }
}
