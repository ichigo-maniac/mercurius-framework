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
     * Data insert component
     */
    public class Insert {
        public static final String COMPONENT_NAME = "insert";
        public static final String ENTITY_NAME = "entity-name";
        public static final String VALUES = "values";
        public static final String COMMON_VALUES = "common-values";
    }

    /**
     * Import column component
     */
    public class ImportColumn {
        public static final String COMPONENT_NAME = "column";
        public static final String PROPERTY = "property";
        public static final String VALUE_IMPORT_BEAN = "value-import-bean";
    }
}
