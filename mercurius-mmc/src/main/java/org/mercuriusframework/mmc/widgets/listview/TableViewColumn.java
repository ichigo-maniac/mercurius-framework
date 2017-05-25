package org.mercuriusframework.mmc.widgets.listview;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.mercuriusframework.mmc.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.providers.EntityMessageSourceProvider;
import org.mercuriusframework.providers.MessageSourceProvider;
import org.mercuriusframework.mmc.renderers.TableViewColumnRenderer;
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
     * Renderer bean
     */
    private TableViewColumnRenderer rendererBean;

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
        if (StringUtils.isEmpty(titleCode)) {
            String entityName = parent.getParent().getEntityName();
            this.title = EntityMessageSourceProvider.getMessage(entityName, MercuriusConstants.LOCALIZATION.ENTITY_PROPERTY_SUFFIX + property);
        } else {
            this.title = MessageSourceProvider.getMessage(titleCode);
        }
        /** Renderer bean */
        String rendererBeanName = columnXmlElement.getAttributes().getNamedItem(
                MercuriusMMCWidgetsConstants.ListView.TableView.Column.RENDERER_BEAN) != null ?
                columnXmlElement.getAttributes().getNamedItem(
                        MercuriusMMCWidgetsConstants.ListView.TableView.Column.RENDERER_BEAN).getNodeValue() : null;
        if (rendererBeanName != null) {
            rendererBean = ApplicationContextProvider.getBean(rendererBeanName.trim(), TableViewColumnRenderer.class);
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
     * Get renderer bean
     * @return Renderer bean
     */
    public TableViewColumnRenderer getRendererBean() {
        return rendererBean;
    }

    /**
     * Set renderer bean
     * @param rendererBean Renderer bean
     */
    public void setRendererBean(TableViewColumnRenderer rendererBean) {
        this.rendererBean = rendererBean;
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
