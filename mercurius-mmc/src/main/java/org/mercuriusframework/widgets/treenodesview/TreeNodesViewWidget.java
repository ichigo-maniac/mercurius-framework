package org.mercuriusframework.widgets.treenodesview;

import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
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
        if (treeNode.getChildNodes() != null) {
            for (int i = 0; i < treeNode.getChildNodes().getLength(); i++) {
                Node nodeElement = treeNode.getChildNodes().item(i);
                if (nodeElement.getNodeName().equals(MercuriusMMCWidgetsConstants.TreeNodesView.TreeNode.WIDGET_NAME)) {
                    nodes.add(new TreeNodeElement(nodeElement));
                }
                if (nodeElement.getNodeName().equals(MercuriusMMCWidgetsConstants.TreeNodesView.TreeNodeEntityElement.WIDGET_NAME)) {
                    nodes.add(new TreeNodeEntityElement(nodeElement));
                }
            }
        }
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
