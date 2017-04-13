package org.mercuriusframework.dataimport.components.update;

import org.mercuriusframework.dataimport.components.common.ImportColumn;
import org.mercuriusframework.dataimport.components.common.SearchComponent;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Update value component
 */
public class UpdateValue {

    /**
     * Import columns
     */
    private List<ImportColumn> columns;

    /**
     * Search component
     */
    private SearchComponent search;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public UpdateValue(Node xmlElement) {
        this.columns = new ArrayList<>();
        /** Parse columns */
        NodeList nodeList = xmlElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.ImportColumn.COMPONENT_NAME)) {
                    columns.add(new ImportColumn(nodeList.item(i)));
                }
            }
        }
        /** Parse search */
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                if (nodeList.item(i).getNodeName().equals(MercuriusDataImportComponentConstants.SearchComponent.COMPONENT_NAME)) {
                    search = new SearchComponent(nodeList.item(i));
                }
            }
        }
    }

    /**
     * Get import columns
     * @return Import columns
     */
    public List<ImportColumn> getColumns() {
        return columns;
    }

    /**
     * Set import columns
     * @param columns Import columns
     */
    public void setColumns(List<ImportColumn> columns) {
        this.columns = columns;
    }

    /**
     * Get search component
     * @return Search component
     */
    public SearchComponent getSearch() {
        return search;
    }

    /**
     * Set search component
     * @param search Search component
     */
    public void setSearch(SearchComponent search) {
        this.search = search;
    }
}
