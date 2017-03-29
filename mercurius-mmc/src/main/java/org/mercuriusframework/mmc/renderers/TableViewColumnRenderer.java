package org.mercuriusframework.mmc.renderers;

/**
 * Table view column renderer interface
 */
public interface TableViewColumnRenderer {

    /**
     * Render column
     * @param parentObject Parent object
     * @param propertyObject Entity object
     * @return Rendered result
     */
    String render(Object parentObject, Object propertyObject);
}
