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

    /**
     * Database flyway migration parameters
     */
    public class DATABASE_FLYWAY_MIGRATION_PARAMETERS {
        public static final String VALIDATE_VERSION = "mercurius.flyway.validate.version";
        public static final String OUT_OF_ORDER = "mercurius.flyway.out.of.order";
        public static final String CLEAN_SCHEMA = "mercurius.flyway.clean.schema";
        public static final String CUSTOM_SCRIPTS_FOLDER = "mercurius.flyway.custom.scripts.folder";
    }

    /**
     * Redis
     */
    public class REDIS_PARAMETERS {
        public static final String SESSION_REDIS_NODE = "mercurius.session.redis.node";
        public static final String SESSION_REDIS_CLUSTER_NODES = "mercurius.session.redis.cluster.nodes";
    }

    /**
     * Web parameters
     */
    public class WEB_PARAMETERS {
        public static final String DEFAULT_CATALOG_CODE = "mercurius.default.catalog.code";
        public static final String CURRENT_STORE_CODE = "mercurius.current.store.code";
        public static final String DEFAULT_UNIT_CODE = "mercurius.default.unit.code";
        public static final String CATALOG_CODE_FOR_DEFAULT_UNIT_CODE = "mercurius.catalog.code.for.default.unit.code";
    }
}
