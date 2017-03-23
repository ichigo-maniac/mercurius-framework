package org.mercuriusframework.dataimport.components.insert;

import org.mercuriusframework.dataimport.components.common.ImportColumn;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert value component
 */
public class InsertValue {
    /**
     * Import columns
     */
    private List<ImportColumn> columns;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public InsertValue(Node xmlElement) {
        this.columns = new ArrayList<>();
        /** Parse columns */
        NodeList nodeList = xmlElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                columns.add(new ImportColumn(nodeList.item(i)));
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
}
