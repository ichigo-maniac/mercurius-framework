package org.mercuriusframework.widgets.listview;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.widgets.Widget;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
            if (currentNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.TableView.WIDGET_NAME)) {
                this.tableView = new TableView(currentNode, this);
                break;
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
}
