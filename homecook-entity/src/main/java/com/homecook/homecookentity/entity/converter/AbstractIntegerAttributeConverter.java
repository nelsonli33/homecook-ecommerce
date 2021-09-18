package com.homecook.homecookentity.entity.converter;


import com.homecook.homecookentity.type.OrdinalType;

import javax.persistence.AttributeConverter;

public abstract class AbstractIntegerAttributeConverter<T extends Enum<T> & OrdinalType>
        implements AttributeConverter<T, Integer>
{
    private final Class<T> clazz;

    public AbstractIntegerAttributeConverter(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    @Override
    public Integer convertToDatabaseColumn(T attribute)
    {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public T convertToEntityAttribute(Integer value)
    {
        if (value != null)
        {
            T[] enums = clazz.getEnumConstants();
            for (T e : enums)
            {
                if (e.getValue() == value)
                {
                    return e;
                }
            }

            throw new UnsupportedOperationException(
                    String.format("%s cannot convert to %s enum value.", value, clazz));
        }
        return null;
    }
}
