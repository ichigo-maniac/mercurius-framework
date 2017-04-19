package org.mercuriusframework.dataimport.constants;

/**
 * Mercurius data import component constants
 */
public class MercuriusDataImportComponentConstants {
    /**
     * Data import component (main component)
     */
    public class DataImport {
        public static final String COMPONENT_NAME = "data-import";
    }

    /**
     * Import component
     */
    public class ImportComponent {
        public static final String ENTITY_NAME = "entity-name";
    }

    /**
     * Data insert component
     */
    public class Insert {
        public static final String COMPONENT_NAME = "insert";
        public static final String ENTITY_NAME = "entity-name";
        public static final String VALUES = "values";
        public static final String COMMON_VALUES = "common-values";
    }

    /**
     * Data update components
     */
    public class Update {
        public static final String COMPONENT_NAME = "update";
        public static final String ENTITY_NAME = "entity-name";
        public static final String VALUES = "values";
        public static final String COMMON_VALUES = "common-values";
        public static final String COMMON_SEARCH = "common-search";
    }

    /**
     * Insert update components
     */
    public class InsertUpdate {
        public static final String COMPONENT_NAME = "insert-update";
    }

    /**
     * Import column component
     */
    public class ImportColumn {
        public static final String COMPONENT_NAME = "column";
        public static final String PROPERTY = "property";
        public static final String VALUE_IMPORT_BEAN = "value-import-bean";
        public static final String INCLUDE_IN_SEARCH = "include-in-search";
    }

    /**
     * Value component
     */
    public class Value {
        public static final String COMPONENT_NAME = "value";
    }

    /**
     * Search component
     */
    public class SearchComponent {
        public static final String COMPONENT_NAME = "search";
        public static final String TEXT_QUERY = "text-query";
    }

    /**
     * Criteria component
     */
    public class CriteriaComponent {
        public static final String COMPONENT_NAME = "criteria";
        public static final String PROPERTY = "property";
        public static final String VALUES = "values";

    }

    /**
     * Criteria value component
     */
    public class CriteriaValueComponent {
        public static final String COMPONENT_NAME = "value";
        public static final String TYPE = "type";
        public static final String VALUE_IMPORT_BEAN = "value-import-bean";
    }
}
