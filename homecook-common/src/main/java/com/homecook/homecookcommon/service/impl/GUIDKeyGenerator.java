package com.homecook.homecookcommon.service.impl;

import com.homecook.homecookcommon.service.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component(value = "guidKeyGenerator")
public class GUIDKeyGenerator implements KeyGenerator
{
    @Override
    public Object generate()
    {
        return UUID.randomUUID().toString();
    }
}
