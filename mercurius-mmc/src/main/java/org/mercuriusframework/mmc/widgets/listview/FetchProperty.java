package org.mercuriusframework.mmc.widgets.listview;

import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.w3c.dom.Node;

/**
 * Fetch property
 */
public class FetchProperty {

    /**
     * Property name
     */
    private String name;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public FetchProperty(Node xmlElement) {
        this.name = xmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.FetchProperties.EntityProperty.NAME).getNodeValue();
    }

    /**
     * Get property name
     * @return Property name
     */
    public String getName() {
        return name;
    }

    /**
     * Set property name
     * @param name Property name
     */
    public void setName(String name) {
        this.name = name;
    }
}
