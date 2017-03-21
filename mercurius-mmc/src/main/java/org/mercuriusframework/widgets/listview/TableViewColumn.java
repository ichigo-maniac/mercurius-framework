package org.mercuriusframework.widgets.listview;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.helpers.MessageSourceProvider;
import org.springframework.context.NoSuchMessageException;
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
     * Parent view
     */
    private TableView parent;

    /**
     * Constructor
     * @param columnXmlElement Table view column xml element
     */
    public TableViewColumn(Node columnXmlElement, TableView parent) {
        this.parent = parent;
        /** Property */
        this.property = columnXmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.TableView.Column.PROPERTY).getNodeValue();
        /** Title */
        String titleCode = columnXmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.TableView.Column.TITLE) != null ?
                columnXmlElement.getAttributes().getNamedItem(
                        MercuriusMMCWidgetsConstants.ListView.TableView.Column.TITLE).getNodeValue() : "";
        try {
            if (StringUtils.isEmpty(titleCode)) {
                String entityName = parent.getParent().getEntityName();
                titleCode = MercuriusConstants.LOCALIZATION.ENTITY_PREFIX + entityName +
                        MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX + property;
            }
            this.title = MessageSourceProvider.getMessage(titleCode);
        } catch (NoSuchMessageException exception) {
            this.title = "[" + titleCode + "]";
        }
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

    /**
     * Get parent view
     * @return Parent view
     */
    public TableView getParent() {
        return parent;
    }

    /**
     * Set parent view
     * @param parent Parent view
     */
    public void setParent(TableView parent) {
        this.parent = parent;
    }
}
