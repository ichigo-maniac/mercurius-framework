package org.mercuriusframework.dataimport.components.common;

import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;

/**
 * Import column
 */
public class ImportColumn {

    /**
     * Include in search
     */
    private boolean includeInSearch;

    /**
     * Property
     */
    private String property;

    /**
     * Value import bean name
     */
    private String valueImportBeanName;

    /**
     * Raw value
     */
    private String rawValue;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public ImportColumn(Node xmlElement) {
        this.property = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.ImportColumn.PROPERTY).getNodeValue();
        this.valueImportBeanName = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.ImportColumn.VALUE_IMPORT_BEAN) != null ?
                xmlElement.getAttributes().getNamedItem(
                        MercuriusDataImportComponentConstants.ImportColumn.VALUE_IMPORT_BEAN).getNodeValue() : null;
        this.rawValue = xmlElement.getTextContent();
        this.includeInSearch = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.ImportColumn.INCLUDE_IN_SEARCH) != null ?
                Boolean.valueOf(xmlElement.getAttributes().getNamedItem(
                        MercuriusDataImportComponentConstants.ImportColumn.INCLUDE_IN_SEARCH).getNodeValue()) : false;
    }

    /**
     * Get include in search
     * @return Include in search flag
     */
    public boolean isIncludeInSearch() {
        return includeInSearch;
    }

    /**
     * Set include in search flag
     * @param includeInSearch Include in search flag
     */
    public void setIncludeInSearch(boolean includeInSearch) {
        this.includeInSearch = includeInSearch;
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
}
