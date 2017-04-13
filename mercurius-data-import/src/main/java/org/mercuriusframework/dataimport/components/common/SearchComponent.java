package org.mercuriusframework.dataimport.components.common;

import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Search component
 */
public class SearchComponent {

    /**
     * Text query
     */
    private String textQuery;

    /**
     * Criterias
     */
    private List<CriteriaComponent> criterias;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public SearchComponent(Node xmlElement) {
        NodeList nodeList = xmlElement.getChildNodes();
        /** Criterias */
        this.criterias = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.CriteriaComponent.COMPONENT_NAME)) {
                    criterias.add(new CriteriaComponent(nodeList.item(i)));
                }
            }
        }
        /** Text query */
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.SearchComponent.TEXT_QUERY)) {
                    this.textQuery = nodeList.item(i).getTextContent();
                    break;
                }
            }
        }
    }

    /**
     * Get text query
     * @return Text query
     */
    public String getTextQuery() {
        return textQuery;
    }

    /**
     * Set text query
     * @param textQuery Text query
     */
    public void setTextQuery(String textQuery) {
        this.textQuery = textQuery;
    }

    /**
     * Get criterias
     * @return Criterias
     */
    public List<CriteriaComponent> getCriterias() {
        return criterias;
    }

    /**
     * Set criterias
     * @param criterias Criterias
     */
    public void setCriterias(List<CriteriaComponent> criterias) {
        this.criterias = criterias;
    }
}
