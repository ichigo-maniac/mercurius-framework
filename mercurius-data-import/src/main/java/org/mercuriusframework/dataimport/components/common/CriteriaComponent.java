package org.mercuriusframework.dataimport.components.common;

import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.mercuriusframework.services.query.CriteriaParameter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Criteria value component
 */
public class CriteriaComponent {

    /**
     * Property
     */
    private String property;

    /**
     * Criteria values
     */
    private List<CriteriaValueComponent> criteriaValues;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public CriteriaComponent(Node xmlElement) {
        this.property = xmlElement.getAttributes().getNamedItem(
                MercuriusDataImportComponentConstants.CriteriaComponent.PROPERTY).getNodeValue();
        /** Criteria values */
        this.criteriaValues = new ArrayList<>();
        NodeList nodeList = xmlElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.CriteriaComponent.VALUES)) {
                    NodeList valuesNodeList = nodeList.item(i).getChildNodes();
                    for (int j = 0; j < valuesNodeList.getLength(); j++) {
                        if (valuesNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            if (valuesNodeList.item(j).getNodeName() == MercuriusDataImportComponentConstants.CriteriaValueComponent.COMPONENT_NAME) {
                                criteriaValues.add(new CriteriaValueComponent(valuesNodeList.item(j)));
                            }
                        }
                    }
                }
            }
        }
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
     * Get criteria values
     * @return Criteria values
     */
    public List<CriteriaValueComponent> getCriteriaValues() {
        return criteriaValues;
    }

    /**
     * Set criteria values
     * @param criteriaValues Criteria values
     */
    public void setCriteriaValues(List<CriteriaValueComponent> criteriaValues) {
        this.criteriaValues = criteriaValues;
    }
}
