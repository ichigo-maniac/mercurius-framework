package org.mercuriusframework.constants;

/**
 * Mercurius manager console widgets constants
 */
public class MercuriusMMCWidgetsConstants {

    /**
     * Config widget
     */
    public class Config {
        public static final String WIDGET_NAME = "config";
    }

    /**
     * Tree nodes view widget
     */
    public class TreeNodesView {

        public static final String WIDGET_NAME = "tree-nodes-view";
        public static final String ROLES  = "roles";
        public static final String PRIORITY = "priority";

        /**
         * Tree node widget
         */
        public class TreeNode {
            public static final String WIDGET_NAME = "tree-node";
            public static final String TITLE = "title";
        }

        /**
         * Tree node entity element widget
         */
        public class TreeNodeEntityElement {
            public static final String WIDGET_NAME = "tree-node-entity-element";
            public static final String TITLE = "title";
            public static final String ENTITY_NAME = "entity-name";
        }
    }

}
