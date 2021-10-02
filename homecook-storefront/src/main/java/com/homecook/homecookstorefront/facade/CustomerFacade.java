package com.homecook.homecookstorefront.facade;

import com.homecook.homecookstorefront.dto.CustomerDTO;
import com.homecook.homecookstorefront.dto.RegisterDTO;

public interface CustomerFacade
{
    CustomerDTO register(RegisterDTO registerDTO);
}
