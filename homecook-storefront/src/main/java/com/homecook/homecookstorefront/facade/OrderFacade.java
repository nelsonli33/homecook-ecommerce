package com.homecook.homecookstorefront.facade;

import com.homecook.homecookstorefront.dto.OrderDTO;

public interface OrderFacade
{
    OrderDTO getOrderDetail(String orderCode);
}
