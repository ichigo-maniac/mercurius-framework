package org.mercuriusframework.mmc.widgets.listview;

import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.mmc.widgets.Widget;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * List view widget
 */
public class ListViewWidget extends Widget {

    /**
     * Table view
     */
    private TableView tableView;

    /**
     * Entity name
     */
    private String entityName;

    /**
     * Fetch properties
     */
    private List<FetchProperty> fetchProperties;

    /**
     * Constructor
     * @param listViewElement List view xml element
     * @param priority Priority
     */
    public ListViewWidget(Node listViewElement, Integer priority) {
        /** Entity name */
        this.entityName = listViewElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.ENTITY_NAME).getNodeValue();
        /** Priority */
        this.setPriority(priority);
        /** Table view */
        NodeList nodeList = listViewElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                if (currentNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.TableView.WIDGET_NAME)) {
                    this.tableView = new TableView(currentNode, this);
                    break;
                }
            }
        }
        /** Fetch properties */
        this.fetchProperties = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                if (currentNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.FetchProperties.WIDGET_NAME)) {
                    NodeList entityProperties = currentNode.getChildNodes();
                    for (int j = 0; j < entityProperties.getLength(); j++) {
                        Node propertyNode = entityProperties.item(j);
                        if (propertyNode.getNodeType() == Node.ELEMENT_NODE) {
                            if (propertyNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.FetchProperties.EntityProperty.WIDGET_NAME)) {
                                fetchProperties.add(new FetchProperty(propertyNode));
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Get table view
     * @return Table view
     */
    public TableView getTableView() {
        return tableView;
    }

    /**
     * Set table view
     * @param tableView Table view
     */
    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    /**
     * Get entity name
     * @return Entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Set entity name
     * @param entityName Entity name
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Get fetch properties
     * @return Fetch properties
     */
    public List<FetchProperty> getFetchProperties() {
        return fetchProperties;
    }

    /**
     * Set fetch properties
     * @param fetchProperties Fetch properties
     */
    public void setFetchProperties(List<FetchProperty> fetchProperties) {
        this.fetchProperties = fetchProperties;
    }

    /**
     * Get fetch fields
     * @return Array of fetch fields
     */
    public String[] getFetchFields() {
        String[] result = new String[fetchProperties.size()];
        for (int i = 0; i < fetchProperties.size(); i++) {
            result[i] = fetchProperties.get(i).getName();
        }
        return result;
    }
}
