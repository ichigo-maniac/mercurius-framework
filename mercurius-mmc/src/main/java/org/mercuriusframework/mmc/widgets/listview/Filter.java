package org.mercuriusframework.mmc.widgets.listview;

import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.w3c.dom.Node;

/**
 * Property filter
 */
public class Filter {

    /**
     * Property
     */
    private String property;

    /**
     * Include on start
     */
    private Boolean includeOnStart;

    /**
     * Constructor
     * @param filterNode Filter xml node
     */
    public Filter(Node filterNode) {
        this.property = filterNode.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.Filters.Filter.PROPERTY).getNodeValue();
        this.includeOnStart = filterNode.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.Filters.Filter.INCLUDE_ON_START) != null ?
                Boolean.valueOf(filterNode.getAttributes().getNamedItem(
                        MercuriusMMCWidgetsConstants.ListView.Filters.Filter.INCLUDE_ON_START).getNodeValue()) : false;

    }

    /**
     * Get property
     * @return Property
     */
    public String getProperty() {
        return property;
    }

    /**
     * Set property
     * @param property Property
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Get include on start
     * @return Include on start
     */
    public Boolean getIncludeOnStart() {
        return includeOnStart;
    }

    /**
     * Set include on start
     * @param includeOnStart Include on start
     */
    public void setIncludeOnStart(Boolean includeOnStart) {
        this.includeOnStart = includeOnStart;
    }
}
