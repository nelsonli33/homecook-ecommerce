package com.homecook.homecookentity.repository.impl;

import com.homecook.homecookentity.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class DefaultCustomJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID>
{
    private final EntityManager em;

    public DefaultCustomJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager)
    {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }


    @Override
    @Transactional
    public void refresh(T t)
    {
        if (em.contains(t))
        {
            em.refresh(t);
        }
    }
}
