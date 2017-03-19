package org.mercuriusframework.widgets.listview;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * List view widget
 */
public class ListViewWidget {

    /**
     * Table view
     */
    private TableView tableView;

    /**
     * Constructor
     * @param listViewElement List view xml element
     */
    public ListViewWidget(Node listViewElement) {
        /** Table view */
        NodeList nodeList = listViewElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.TableView.WIDGET_NAME)) {
                this.tableView = new TableView(currentNode);
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
}
