package org.mercuriusframework.dataimport.components.common;

import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;

/**
 * Criteria value component
 */
public class CriteriaValueComponent {

    /**
     * Value import bean name
     */
    private String valueImportBeanName;

    /**
     * Raw value
     */
    private String rawValue;

    /**
     * Type
     */
    private String type;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public CriteriaValueComponent(Node xmlElement) {
        this.type = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.CriteriaValueComponent.TYPE).getNodeValue();
        this.valueImportBeanName = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.CriteriaValueComponent.VALUE_IMPORT_BEAN) != null ?
                xmlElement.getAttributes().getNamedItem(
                        MercuriusDataImportComponentConstants.CriteriaValueComponent.VALUE_IMPORT_BEAN).getNodeValue() : null;
        this.rawValue = xmlElement.getTextContent();
    }

    /**
     * Constructor
     * @param valueImportBeanName Value import bean name
     * @param rawValue Raw value
     * @param type Type
     */
    public CriteriaValueComponent(String valueImportBeanName, String rawValue, String type) {
        this.valueImportBeanName = valueImportBeanName;
        this.rawValue = rawValue;
        this.type = type;
    }

    /**
     * Get value import bean name
     * @return Value import bean name
     */
    public String getValueImportBeanName() {
        return valueImportBeanName;
    }

    /**
     * Set value import bean name
     * @param valueImportBeanName Value import bean name
     */
    public void setValueImportBeanName(String valueImportBeanName) {
        this.valueImportBeanName = valueImportBeanName;
    }

    /**
     * Get raw value
     * @return Raw value
     */
    public String getRawValue() {
        return rawValue;
    }

    /**
     * Set raw value
     * @param rawValue Raw value
     */
    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    /**
     * Get type
     * @return Type
     */
    public String getType() {
        return type;
    }

    /**
     * Set type
     * @param type Type
     */
    public void setType(String type) {
        this.type = type;
    }
}
