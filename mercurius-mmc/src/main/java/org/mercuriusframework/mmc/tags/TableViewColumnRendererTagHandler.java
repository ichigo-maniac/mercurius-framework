package org.mercuriusframework.mmc.tags;

import org.mercuriusframework.mmc.renderers.TableViewColumnRenderer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Tag view column renderer tag handler
 */
public class TableViewColumnRendererTagHandler extends SimpleTagSupport {

    /**
     * Parent object
     */
    private Object parentObject;

    /**
     * Property object
     */
    private Object propertyObject;

    /**
     * Column renderer
     */
    private TableViewColumnRenderer columnRenderer;

    /**
     * Do tag
     * @throws JspException JSP exception
     * @throws IOException IO exception
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter writer = getJspContext().getOut();
        writer.println(columnRenderer.render(parentObject, propertyObject));
    }

    /**
     * Set parent object
     * @param parentObject Parent object
     */
    public void setParentObject(Object parentObject) {
        this.parentObject = parentObject;
    }

    /**
     * Set property object
     * @param propertyObject Property object
     */
    public void setPropertyObject(Object propertyObject) {
        this.propertyObject = propertyObject;
    }

    /**
     * Set column renderer
     * @param columnRenderer Column renderer
     */
    public void setColumnRenderer(TableViewColumnRenderer columnRenderer) {
        this.columnRenderer = columnRenderer;
    }
}
