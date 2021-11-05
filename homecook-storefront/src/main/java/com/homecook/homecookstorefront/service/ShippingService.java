package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.ShippingModeEntity;

import java.util.List;

public interface ShippingService
{
    ShippingModeEntity getShippingModeForCode(String code);

    List<String> getAllCVSCodes();

    List<ShippingModeEntity> getSupportedShippingModesForCart(CartEntity cart);

    List<AddressEntity> getSupportedShippingAddressesForCart(CartEntity cart);
}
