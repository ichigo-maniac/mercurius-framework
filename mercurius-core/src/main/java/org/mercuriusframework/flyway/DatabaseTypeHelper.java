package org.mercuriusframework.flyway;

import java.util.HashMap;
import java.util.Map;

/**
 * Database type enum
 */
public class DatabaseTypeHelper {
    /**
     * Database types map
     */
    private static Map<String, String> DATABASE_TYPES_MAP = new HashMap<String, String>();

    /**
     * Map initialization
     */
    static {
        DATABASE_TYPES_MAP.put("org.postgresql.Driver", "postgresql");
        DATABASE_TYPES_MAP.put("oracle.jdbc.driver.OracleDriver", "oracle");
    }

    /**
     * Get migration scripts folder name
     * @param driverName Database driver name
     * @return Folder name
     */
    public static String getScriptsFolderName(String driverName) {
        return DATABASE_TYPES_MAP.get(driverName.trim());
    }
}
