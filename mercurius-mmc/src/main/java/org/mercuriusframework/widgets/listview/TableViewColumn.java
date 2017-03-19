package org.mercuriusframework.widgets.listview;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.w3c.dom.Node;

/**
 * Table view column
 */
public class TableViewColumn {

    /**
     * Title
     */
    private String title;

    /**
     * Property
     */
    private String property;

    /**
     * Constructor
     * @param columnXmlElement Table view column xml element
     */
    public TableViewColumn(Node columnXmlElement) {
        this.title = columnXmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.TableView.Column.TITLE).getNodeValue();
        this.property = columnXmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.TableView.Column.PROPERTY).getNodeValue();
    }

    /**
     * Get title
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title
     * @param title Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get property
     * @return Property
     */
    public String getProperty() {
        return property;
    }

    /**
     * Set property
     * @param property Property
     */
    public void setProperty(String property) {
        this.property = property;
    }
}
