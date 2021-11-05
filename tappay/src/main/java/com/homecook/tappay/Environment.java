package com.homecook.tappay;

import java.util.TreeMap;

public enum Environment
{
    PRODUCTION,

    SANDBOX;

    private static TreeMap<String, Environment> valueMap = new TreeMap<>();
    private String value;

    static
    {
        SANDBOX.value = "Sandbox";
        PRODUCTION.value = "Production";

        valueMap.put("Sandbox", Environment.SANDBOX);
        valueMap.put("Production", Environment.PRODUCTION);
    }


    public static Environment fromString(String env)
    {
        return valueMap.get(env);
    }

    public String value()
    {
        return value;
    }
}
