package com.homecook.homecookstorefront.service.strategy;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface RemoveLineItemsStrategy
{
    CartEntity removeAllLineItems(final CommerceCartParameter parameter);
}
