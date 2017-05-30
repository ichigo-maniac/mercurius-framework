package org.mercuriusframework.dataimport.constants;

/**
 * Mercurius data import constants
 */
public class MercuriusDataImportConstants {
    /**
     * Common constants
     */
    public class COMMON {
        public static final String DATA_IMPORT_ROLE_CODE = "ROLE_DATA_IMPORT";
    }
    /**
     * URL constants
     */
    public class URLS {
        public static final String BASE_PATH = "/dataimport/";
        public static final String BASE_APPLICATION_PATH = "/dataimport/app_panel";
        public static final String HOME_PATH = "/";
        public static final String IMPORT_STRING_DATA = "/import_string_data";
        public static final String IMPORT_FILES_DATA = "/import_files_data";
        public static final String IMPORT_PACKAGE_DATA = "/import_package_data";
    }

    /**
     * JSP templates constants
     */
    public class JSP_TEMPLATES {
        public static final String HOME_JSP = "dataimport/index";
        public static final String MAIN_VIEW_JSP = "dataimport/main_view";
        public static final String FILES_IMPORT = "dataimport/files_import/files_import";
        public static final String PACKAGE_IMPORT = "dataimport/package_import/package_import";
    }
}
