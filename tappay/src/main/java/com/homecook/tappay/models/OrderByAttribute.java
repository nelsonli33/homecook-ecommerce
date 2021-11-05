package com.homecook.tappay.models;

import java.util.TreeMap;

public enum OrderByAttribute
{
    TIME,

    AMOUNT;

    private static TreeMap<String, OrderByAttribute> valueMap = new TreeMap<>();
    private String value;

    static
    {
        TIME.value = "time";
        AMOUNT.value = "amount";

        valueMap.put("time", OrderByAttribute.TIME);
        valueMap.put("amount", OrderByAttribute.AMOUNT);
    }


    public static OrderByAttribute fromString(String value)
    {
        return valueMap.get(value);
    }

    public String value()
    {
        return value;
    }
}
