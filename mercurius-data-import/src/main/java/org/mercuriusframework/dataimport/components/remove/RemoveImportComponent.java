package org.mercuriusframework.dataimport.components.remove;

import org.mercuriusframework.dataimport.components.AbstractImportComponent;
import org.mercuriusframework.dataimport.components.common.CriteriaComponent;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove data import component
 */
public class RemoveImportComponent extends AbstractImportComponent {

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
     * @param xmlElement XML component
     */
    public RemoveImportComponent(Node xmlElement) {
        super(xmlElement);
        this.criterias = new ArrayList<>();
        NodeList nodeList = xmlElement.getChildNodes();
        /** Text query */
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.Remove.TEXT_QUERY)) {
                    this.textQuery = nodeList.item(i).getTextContent();
                    break;
                }
            }
        }
        /** Criterias */
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.Remove.CRITERIA_QUERY)) {
                    Node criteriaQueryElement = nodeList.item(i);
                    NodeList criteriasElements = criteriaQueryElement.getChildNodes();
                    for (int j = 0; j < criteriasElements.getLength(); j++) {
                        if (criteriasElements.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            criterias.add(new CriteriaComponent(criteriasElements.item(j)));
                        }
                    }
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
