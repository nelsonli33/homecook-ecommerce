package com.homecook.homecookentity.entity.converter;

import com.homecook.homecookentity.type.StringType;

import javax.persistence.AttributeConverter;


public abstract class AbstractStringAttributeConverter<T extends Enum<T> & StringType>
        implements AttributeConverter<T, String>
{
    private final Class<T> clazz;

    public AbstractStringAttributeConverter(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    @Override
    public String convertToDatabaseColumn(T attribute)
    {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData)
    {
        if (dbData != null)
        {
            T[] enums = clazz.getEnumConstants();
            for (T e : enums)
            {
                if (e.getCode().equals(dbData))
                {
                    return e;
                }
            }

            throw new UnsupportedOperationException(
                    String.format("%s cannot convert to %s enum value.", dbData, clazz));
        }
        return null;
    }
}
