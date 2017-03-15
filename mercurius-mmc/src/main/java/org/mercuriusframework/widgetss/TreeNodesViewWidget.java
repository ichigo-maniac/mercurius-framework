package org.mercuriusframework.widgetss;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Tree nodes widget
 */
public class TreeNodesViewWidget {

    /**
     * Get nodes
     */
    protected List<TreeNode> nodes;

    /**
     * Constructor
     */
    public TreeNodesViewWidget(Node treeNode) {
        this.nodes = new ArrayList<>();
        /** Parse tree node */
    }

    /**
     * Get nodes
     * @return Nodes
     */
    public List<TreeNode> getNodes() {
        return nodes;
    }

    /**
     * Set nodes
     * @param nodes Nodes
     */
    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}
