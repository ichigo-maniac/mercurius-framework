package org.mercuriusframework.mmc.services;

import org.mercuriusframework.mmc.dto.FilterContainer;
import org.mercuriusframework.mmc.widgets.listview.Filter;

import java.util.List;

/**
 * MMC filter service interface
 */
public interface MMCFilterService {

    /**
     * Build filters
     * @param entityName Entity name
     * @param filters List of filters
     * @return List of filters containers
     */
    List<FilterContainer> buildFilters(String entityName, List<Filter> filters);
}
