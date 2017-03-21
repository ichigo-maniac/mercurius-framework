package org.mercuriusframework.widgets.listview;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Table view element
 */
public class TableView {

    /**
     * Columns
     */
    private List<TableViewColumn> columns;

    /**
     * Parent view
     */
    private ListViewWidget parent;

    /**
     * Constructor
     * @param tableViewXmlElement Table view xml element
     */
    public TableView(Node tableViewXmlElement, ListViewWidget parent) {
        this.parent = parent;
        /** Columns */
        this.columns = new ArrayList<>();
        NodeList nodeList = tableViewXmlElement.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeName().equals(MercuriusMMCWidgetsConstants.ListView.TableView.Column.WIDGET_NAME)) {
                columns.add(new TableViewColumn(currentNode, this));
            }
        }
    }

    /**
     * Get columns
     * @return Columns
     */
    public List<TableViewColumn> getColumns() {
        return columns;
    }

    /**
     * Set columns
     * @param columns Columns
     */
    public void setColumns(List<TableViewColumn> columns) {
        this.columns = columns;
    }

    /**
     * Get parent view
     * @return Parent view
     */
    public ListViewWidget getParent() {
        return parent;
    }

    /**
     * Set parent view
     * @param parent Parent view
     */
    public void setParent(ListViewWidget parent) {
        this.parent = parent;
    }
}
