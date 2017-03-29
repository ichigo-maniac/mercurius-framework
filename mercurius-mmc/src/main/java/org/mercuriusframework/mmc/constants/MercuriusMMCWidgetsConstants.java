package org.mercuriusframework.mmc.constants;

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

    /**
     * List view (entities)
     */
    public class ListView {

        public static final String WIDGET_NAME = "list-view";
        public static final String ENTITY_NAME = "entity-name";
        public static final String ROLES  = "roles";
        public static final String PRIORITY = "priority";

        /**
         * Fetch properties
         */
        public class FetchProperties {

            public static final String WIDGET_NAME = "fetch-properties";

            /**
             * Entity property
             */
            public class EntityProperty {
                public static final String WIDGET_NAME = "entity-property";
                public static final String NAME = "name";
            }
        }

        /**
         * Table view
         */
        public class TableView {

            public static final String WIDGET_NAME = "table-view";

            /**
             * Table view column
             */
            public class Column {
                public static final String WIDGET_NAME = "column";
                public static final String TITLE = "title";
                public static final String PROPERTY = "property";
                public static final String RENDERER_BEAN = "rendererBean";
            }
        }
    }

}
