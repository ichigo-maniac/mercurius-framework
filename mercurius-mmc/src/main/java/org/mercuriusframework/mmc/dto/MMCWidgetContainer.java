package org.mercuriusframework.mmc.dto;

import org.w3c.dom.Node;

/**
 * Mercurius manager console widget container
 */
public class MMCWidgetContainer {

    /**
     * XML element
     */
    private Node xmlElement;
    /**
     * Priority
     */
    private Integer priority;

    /**
     * Constructor
     * @param xmlElement XML element
     * @param priority Priority
     */
    public MMCWidgetContainer(Node xmlElement, Integer priority) {
        this.xmlElement = xmlElement;
        this.priority = priority != null ? priority : 0;
    }

    /**
     * Get XML element
     * @return XML element
     */
    public Node getXmlElement() {
        return xmlElement;
    }

    /**
     * Set XML element
     * @param xmlElement XML element
     */
    public void setXmlElement(Node xmlElement) {
        this.xmlElement = xmlElement;
    }

    /**
     * Get priority
     * @return Priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Set priority
     * @param priority Priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
