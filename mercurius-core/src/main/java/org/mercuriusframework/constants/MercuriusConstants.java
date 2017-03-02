package org.mercuriusframework.constants;

/**
 * Mercurius constants
 */
public class MercuriusConstants {
    /**
     * Common constants
     */
    public class COMMON {
        public static final String MERCURIUS_BASE_PACKAGE = "org.mercuriusframework";
        public static final String MERCURIUS_BASE_PACKAGE_FOR_SCAN = "org.mercuriusframework.*";
        public static final String APPLICATION_PROPERTIES_PATH = "classpath:application.properties";
    }
    /**
     * Profiles constants
     */
    public class PROFILES {
        public static final String DEVELOP_PROFILE = "mercurius_develop_profile";
        public static final String TEST_PROFILE = "mercurius_test_profile";
        public static final String REDIS_SESSION_PROFILE = "mercurius_redis_session_profile";
    }

    /**
     * Session attributes constants
     */
    public class SESSION_ATTRIBUTES {
        public static final String DEFAULT_CATALOG = "defaultCatalog";
        public static final String CURRENT_STORE = "currentStore";
        public static final String DEFAULT_UNIT = "defaultUnit";
    }
}
