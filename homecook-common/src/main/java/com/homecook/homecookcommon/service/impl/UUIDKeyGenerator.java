package com.homecook.homecookcommon.service.impl;

import com.homecook.homecookcommon.service.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component(value = "uuidKeyGenerator")
public class UUIDKeyGenerator implements KeyGenerator
{
    @Override
    public Object generate()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
