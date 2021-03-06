package org.mercuriusframework.dataimport.components.insert;

import org.mercuriusframework.dataimport.components.AbstractImportComponent;
import org.mercuriusframework.dataimport.components.common.ImportColumn;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportComponentConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert import component
 */
public class InsertImportComponent extends AbstractImportComponent {

    /**
     * Common import columns
     */
    private List<ImportColumn> commonColumns;

    /**
     * Import values
     */
    private List<InsertValue> values;

    /**
     * Constructor
     * @param xmlElement Xml element
     */
    public InsertImportComponent(Node xmlElement) {
        super(xmlElement);
        /** Columns */
        this.commonColumns = new ArrayList<>();
        this.values = new ArrayList<>();
        /** Parse common columns */
        NodeList childNodes = xmlElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals(MercuriusDataImportComponentConstants.Insert.COMMON_VALUES)) {
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
                if (node.getNodeName().equals(MercuriusDataImportComponentConstants.Insert.VALUES)) {
                    NodeList valuesNodeList = node.getChildNodes();
                    for (int j = 0; j < valuesNodeList.getLength(); j++) {
                        if (valuesNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            if (valuesNodeList.item(j).getNodeName().equals(MercuriusDataImportComponentConstants.Value.COMPONENT_NAME)) {
                                values.add(new InsertValue(valuesNodeList.item(j)));
                            }
                        }
                    }
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
     * Get import values
     * @return Import values
     */
    public List<InsertValue> getValues() {
        return values;
    }

    /**
     * Set import values
     * @param values Import values
     */
    public void setValues(List<InsertValue> values) {
        this.values = values;
    }
}
