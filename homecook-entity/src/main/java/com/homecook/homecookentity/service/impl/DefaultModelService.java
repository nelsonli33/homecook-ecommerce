package com.homecook.homecookentity.service.impl;

import com.homecook.homecookentity.entity.AbstractBaseEntity;
import com.homecook.homecookentity.repository.ModelRepository;
import com.homecook.homecookentity.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service(value = "modelService")
public class DefaultModelService implements ModelService
{
    private ModelRepository<AbstractBaseEntity> modelRepository;

    @Autowired
    public DefaultModelService(ModelRepository<AbstractBaseEntity> modelRepository)
    {
        this.modelRepository = modelRepository;
    }

    @Override
    public void save(AbstractBaseEntity model)
    {
        modelRepository.saveAndFlush(model);
//        modelRepository.save(model);
//        modelRepository.refresh(model);
    }

    @Override
    public void refresh(AbstractBaseEntity model)
    {
        modelRepository.refresh(model);
    }

    @Override
    public void saveAll(Collection<? extends AbstractBaseEntity> models)
    {
        modelRepository.saveAllAndFlush(models);
    }


    @Override
    public void remove(AbstractBaseEntity model)
    {
        modelRepository.delete(model);
    }

    @Override
    public void removeAll(Iterable<? extends AbstractBaseEntity> models)
    {
        modelRepository.deleteAll(models);
    }
}
