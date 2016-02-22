package org.mercuriusframework.constants;

/**
 * Necessary mercurius framework configuration parameters
 */
public class MercuriusConfigurationParameters {
    /**
     * Database connection parameters
     */
    public class DATABASE_PARAMETERS {
        public static final String DRIVER_CLASS_NAME = "mercurius.driver.class.name";
        public static final String DATABASE_URL = "mercurius.database.url";
        public static final String DATABASE_USERNAME = "mercurius.database.username";
        public static final String DATABASE_PASSWORD = "mercurius.database.password";
        public static final String DATABASE_HIBERNATE_DIALECT = "mercurius.database.hibernate.dialect";
        public static final String SHOW_SQL_LOG = "mercurius.show.sql.log";
    }
}
