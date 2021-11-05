package com.homecook.homecookentity.type;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public interface StringType
{
    String getCode();

    static <E extends Enum> Map getValues(Class clazz)
    {
        Map m = new HashMap();

        for (Object e : EnumSet.allOf(clazz))
        {
            m.put(((StringType) e).getCode(), (E) e);
        }

        return m;
    }
}
