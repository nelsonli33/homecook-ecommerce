package com.homecook.homecookcommon.converter;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface Converter<SOURCE, TARGET>
{
    TARGET convert(SOURCE source);

    default List<TARGET> convertAll(Collection<? extends SOURCE> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        } else {
            List<TARGET> result = new ArrayList<>(sources.size());

            for(SOURCE source : sources) {
                result.add(this.convert(source));
            }

            return result;
        }
    }
}
