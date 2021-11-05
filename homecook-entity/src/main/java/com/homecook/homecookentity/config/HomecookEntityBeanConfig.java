package com.homecook.homecookentity.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class HomecookEntityBeanConfig
{
    @PersistenceContext
    private EntityManager entityManager;

    @Bean(name = "queryFactory")
    public JPAQueryFactory queryFactory()
    {
        return new JPAQueryFactory(entityManager);
    }
}
