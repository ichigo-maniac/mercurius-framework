package org.mercuriusframework.mmc.widgets.listview;

import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.mmc.enums.FilterConcatType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Filters view
 */
public class FiltersView {

    /**
     * Filters
     */
    private List<Filter> filters;

    /**
     * Default concatenation type
     */
    private FilterConcatType defaultConcatType;

    /**
     * Constructor
     * @param filtersNode Filters xml node
     */
    public FiltersView(Node filtersNode) {
        this.defaultConcatType = filtersNode.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.Filters.DEFAULT_CONCAT_TYPE) != null ?
                FilterConcatType.valueFromString(filtersNode.getAttributes().getNamedItem(
                        MercuriusMMCWidgetsConstants.ListView.Filters.DEFAULT_CONCAT_TYPE).getNodeValue()) :FilterConcatType.AND;
        /** Filters */
        this.filters = new ArrayList<>();
        NodeList filtersNodes = filtersNode.getChildNodes();
        for (int j = 0; j < filtersNodes.getLength(); j++) {
            Node filterNode = filtersNodes.item(j);
            if (filterNode.getNodeType() == Node.ELEMENT_NODE) {
                if (filterNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.Filters.Filter.WIDGET_NAME)) {
                    Filter filter = new Filter(filterNode);
                    filters.add(filter);
                }
            }
        }
    }

    /**
     * Get all filters
     * @return List of filters
     */
    public List<Filter> getFilters() {
        return filters;
    }

    /**
     * Set all filters
     * @param filters List of filters
     */
    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    /**
     * Get default concatenation type
     * @return Default concatenation type
     */
    public FilterConcatType getDefaultConcatType() {
        return defaultConcatType;
    }

    /**
     * Set default concatenation type
     * @param defaultConcatType Default concatenation type
     */
    public void setDefaultConcatType(FilterConcatType defaultConcatType) {
        this.defaultConcatType = defaultConcatType;
    }
}
