package com.homecook.homecookcommon.converter;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface Mapper<M, D>
{
    D convertToDto(M model);

    M convertToModel(D data);

    void populateModelToDto(M model, D data);

    void populateDtoToModel(D data, M model);


    default List<D> convertAllToDto(Collection<? extends M> sources) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        } else {
            List<D> result = new ArrayList<>(sources.size());

            for(M source : sources) {
                result.add(this.convertToDto(source));
            }

            return result;
        }
    }

    default List<M> convertAllToModels(Collection<? extends D> targets) {
        if (CollectionUtils.isEmpty(targets)) {
            return Collections.emptyList();
        } else {
            List<M> result = new ArrayList<>(targets.size());

            for(D target : targets) {
                result.add(this.convertToModel(target));
            }

            return result;
        }
    }
}
