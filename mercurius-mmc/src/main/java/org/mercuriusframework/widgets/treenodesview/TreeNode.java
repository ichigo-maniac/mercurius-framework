package org.mercuriusframework.widgets.treenodesview;

/**
 * Tree node element interface
 */
public abstract class TreeNode {

    /**
     * Title
     */
    protected String title;

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
     * Get type
     * @return Type
     */
    public abstract String getType();
}
