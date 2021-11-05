package com.homecook.tappay.models;

import java.util.TreeMap;

public enum RecordStatus
{
    ERROR,

    AUTH,

    OK,

    PARTIALREFUNDED,

    REFUNDED,

    PENDING,

    CANCEL;

    private static TreeMap<Integer, RecordStatus> valueMap = new TreeMap<>();
    private int value;

    static
    {
        ERROR.value = -1;
        AUTH.value = 0;
        OK.value = 1;
        PARTIALREFUNDED.value = 2;
        REFUNDED.value = 3;
        PENDING.value = 4;
        CANCEL.value = 5;

        valueMap.put(-1, RecordStatus.ERROR);
        valueMap.put(0, RecordStatus.AUTH);
        valueMap.put(1, RecordStatus.OK);
        valueMap.put(2, RecordStatus.PARTIALREFUNDED);
        valueMap.put(3, RecordStatus.REFUNDED);
        valueMap.put(4, RecordStatus.PENDING);
        valueMap.put(5, RecordStatus.CANCEL);
    }


    public static RecordStatus fromValue(int value)
    {
        return valueMap.get(value);
    }

    public int value()
    {
        return value;
    }
}
