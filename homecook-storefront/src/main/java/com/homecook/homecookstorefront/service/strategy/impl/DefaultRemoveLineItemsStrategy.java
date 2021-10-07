package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.service.strategy.RemoveLineItemsStrategy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNull;

@Component(value = "removeLineItemsStrategy")
public class DefaultRemoveLineItemsStrategy extends AbstractCartStrategy
        implements RemoveLineItemsStrategy
{
    @Override
    @Transactional
    public CartEntity removeAllLineItems(CommerceCartParameter parameter)
    {
        final CartEntity cartEntity = parameter.getCart();
        validateParameterNotNull(cartEntity, "cart entity cannot be null");

        cartEntity.getLineItems().clear();
        getModelService().save(cartEntity);

        getCartCalculationStrategy().calculateCart(parameter);

        return cartEntity;
    }
}
