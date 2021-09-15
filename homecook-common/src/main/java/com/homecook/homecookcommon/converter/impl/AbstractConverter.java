package com.homecook.homecookcommon.converter.impl;

import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookcommon.converter.Populator;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

/**
 * Abstract implementation of the Converter interface that also supports the Populator interface. Implementations of
 * this base type can either be used as a converter or as a populator. When used as a converter the
 * {@link #createFromClass()} method is called to create the target instance and then the {@link #populate(Object, Object)}
 * method is called to populate the target with values from the source instance.
 * @param <SOURCE> the type of the source object
 * @param <TARGET> the type of the target object
 */
public abstract class AbstractConverter<SOURCE, TARGET>
        implements Converter<SOURCE, TARGET>, Populator<SOURCE, TARGET>, BeanNameAware
{
    private Class<TARGET> targetClass;

    @Override
    public TARGET convert(SOURCE source)
    {
        TARGET target = createFromClass();
        populate(source, target);
        return target;
    }

    /**
     * Allows to specify the target object class directly.
     */
    public void setTargetClass(final Class<TARGET> targetClass)
    {
        this.targetClass = targetClass;

        // sanity check - can we instantiate that class ?
        if (targetClass != null)
        {
            createFromClass();
        }
    }

    protected TARGET createFromClass()
    {
        try
        {
            return targetClass.getDeclaredConstructor().newInstance();
        }
        catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }


    // -----------------------------------------------------------------------------
    // --- Sanity check for the converter setups
    // -----------------------------------------------------------------------------

    private String myBeanName;


    @Override
    public void setBeanName(String name)
    {
        this.myBeanName = name;
    }

    public String getMyBeanName()
    {
        return myBeanName;
    }

    @PostConstruct
    public void init() throws Exception
    {
        if (targetClass == null)
        {
            throw new IllegalStateException(
                    "Converter '" + myBeanName + "' doesn't have a targetClass property!");
        }
    }

}
