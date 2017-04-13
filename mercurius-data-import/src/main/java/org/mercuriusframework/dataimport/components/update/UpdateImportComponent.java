package org.mercuriusframework.dataimport.components.update;

import org.mercuriusframework.dataimport.components.AbstractImportComponent;
import org.mercuriusframework.dataimport.components.common.ImportColumn;
import org.mercuriusframework.dataimport.components.common.SearchComponent;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Update import component
 */
public class UpdateImportComponent extends AbstractImportComponent {

    /**
     * Common import columns
     */
    private List<ImportColumn> commonColumns;

    /**
     * Common search
     */
    private SearchComponent commonSearch;

    /**
     * Values
     */
    private List<UpdateValue> values;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public UpdateImportComponent(Node xmlElement) {
        super(xmlElement);
        /** Columns */
        this.commonColumns = new ArrayList<>();
        this.values = new ArrayList<>();
        /** Parse common columns */
        NodeList childNodes = xmlElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(MercuriusDataImportComponentConstants.Update.COMMON_VALUES)) {
                    NodeList commonValuesNodeList = node.getChildNodes();
                    for (int j = 0; j < commonValuesNodeList.getLength(); j++) {
                        if (commonValuesNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            if (commonValuesNodeList.item(j).getNodeName().equals(MercuriusDataImportComponentConstants.ImportColumn.COMPONENT_NAME)) {
                                commonColumns.add(new ImportColumn(commonValuesNodeList.item(j)));
                            }
                        }
                    }
                    break;
                }
            }
        }
        /** Parse values */
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(MercuriusDataImportComponentConstants.Update.VALUES)) {
                    NodeList valuesNodeList = node.getChildNodes();
                    for (int j = 0; j < valuesNodeList.getLength(); j++) {
                        if (valuesNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            if (valuesNodeList.item(j).getNodeName().equals(MercuriusDataImportComponentConstants.Value.COMPONENT_NAME)) {
                                values.add(new UpdateValue(valuesNodeList.item(j)));
                            }
                        }
                    }
                    break;
                }
            }
        }
        /** Parse common search */
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(MercuriusDataImportComponentConstants.Update.COMMON_SEARCH)) {
                    commonSearch = new SearchComponent(node);
                    break;
                }
            }
        }
    }

    /**
     * Get common import columns
     * @return Common import columns
     */
    public List<ImportColumn> getCommonColumns() {
        return commonColumns;
    }

    /**
     * Set common import columns
     * @param commonColumns Common import columns
     */
    public void setCommonColumns(List<ImportColumn> commonColumns) {
        this.commonColumns = commonColumns;
    }

    /**
     * Get common search
     * @return Common search
     */
    public SearchComponent getCommonSearch() {
        return commonSearch;
    }

    /**
     * Set common search
     * @param commonSearch Common search
     */
    public void setCommonSearch(SearchComponent commonSearch) {
        this.commonSearch = commonSearch;
    }

    /**
     * Get values
     * @return Values
     */
    public List<UpdateValue> getValues() {
        return values;
    }

    /**
     * Set values
     * @param values Values
     */
    public void setValues(List<UpdateValue> values) {
        this.values = values;
    }
}
