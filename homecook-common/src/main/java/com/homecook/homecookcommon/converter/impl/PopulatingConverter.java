package com.homecook.homecookcommon.converter.impl;

import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookcommon.converter.PopulatorList;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Populating converter that uses a list of populators to populate the target during conversion.
 *
 * @param <SOURCE> the type of the source object
 * @param <TARGET> the type of the target object
 */
public class PopulatingConverter<SOURCE, TARGET> extends AbstractConverter<SOURCE, TARGET>
        implements PopulatorList<SOURCE, TARGET>
{
    private static final Logger log = LoggerFactory.getLogger(PopulatingConverter.class);

    private List<Populator<SOURCE, TARGET>> populators;

    @Override
    public List<Populator<SOURCE, TARGET>> getPopulators()
    {
        return populators;
    }

    @Override
    public void setPopulators(List<Populator<SOURCE, TARGET>> populators)
    {
        this.populators = populators;
    }

    @Override
    public void populate(SOURCE source, TARGET target)
    {
        final List<Populator<SOURCE, TARGET>> populatorList = getPopulators();

        if (populatorList == null)
        {
            return;
        }

        for (final Populator<SOURCE, TARGET> populator : populatorList)
        {
            if (populator != null)
            {
                populator.populate(source, target);
            }
        }
    }

    // execute when BEAN name is known
    @PostConstruct
    public void removePopulatorsDuplicates()
    {
        // check for populators duplicates
        if (CollectionUtils.isNotEmpty(populators))
        {
            final LinkedHashSet<Populator<SOURCE, TARGET>> distinctPopulators = new LinkedHashSet<>();

            for (final Populator<SOURCE, TARGET> populator : populators)
            {
                if (!distinctPopulators.add(populator))
                {
                    log.warn("Duplicate populator entry [" + populator.getClass().getName() + "] found for converter "
                            + getMyBeanName() + "! The duplication has been removed.");
                }
            }
            this.populators = new ArrayList<>(distinctPopulators);
        }
        else
        {
            log.warn("Empty populators list found for converter " + getMyBeanName() + "!");
        }
    }


}
