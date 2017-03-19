package org.mercuriusframework.services;

import org.mercuriusframework.enums.WidgetType;
import org.w3c.dom.Node;

/**
 * MMC application service interface
 */
public interface MMCApplicationService {

    /**
     * Build application (if application has been built - nothing's gonna happen)
     */
    void build();

    /**
     * Rebuild application (if application hasn't been built - this call's building it)
     */
    void rebuild();

    /**
     * Get widget xml element
     * @param widgetType Widget type
     * @return Widget xml element
     */
    Node getWidgetXmlElement(WidgetType widgetType);

    /**
     * Get entity widget xml element
     * @param widgetType Widget type
     * @param entityName Entity name
     * @return Widget xml element
     */
    Node getEntityWidgetXmlElement(WidgetType widgetType, String entityName);

    /**
     * Is application has been build
     * @return Check result
     */
    boolean isApplicationBuilt();
}
