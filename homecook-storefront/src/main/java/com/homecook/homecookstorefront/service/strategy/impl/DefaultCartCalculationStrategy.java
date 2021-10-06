package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.service.CalculationService;
import com.homecook.homecookstorefront.service.strategy.CartCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNull;

@Component(value = "cartCalculationStrategy")
public class DefaultCartCalculationStrategy implements CartCalculationStrategy
{
    private CalculationService calculationService;

    @Autowired
    public DefaultCartCalculationStrategy(CalculationService calculationService)
    {
        this.calculationService = calculationService;
    }

    @Override
    public void calculateCart(CommerceCartParameter parameter)
    {
        final CartEntity cartEntity = parameter.getCart();

        validateParameterNotNull(cartEntity, "Cart entity cannot be null");

        calculationService.calculate(cartEntity);
    }

}
