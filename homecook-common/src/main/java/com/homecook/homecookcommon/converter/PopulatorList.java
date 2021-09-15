package com.homecook.homecookcommon.converter;

import java.util.List;

/**
 * Interface for a list of populators.
 *
 * @param <SOURCE> the type of the source object
 * @param <TARGET> the type of the destination object
 */
public interface PopulatorList<SOURCE, TARGET>
{
    List<Populator<SOURCE, TARGET>> getPopulators();

    void setPopulators(List<Populator<SOURCE, TARGET>> populators);
}
