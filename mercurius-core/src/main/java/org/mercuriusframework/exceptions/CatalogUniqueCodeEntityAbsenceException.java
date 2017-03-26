package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Catalog unique code absence exception
 */
public class CatalogUniqueCodeEntityAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param entityName Entity name
     * @param entityCode Entity code
     * @param catalogCode Catalog code
     */
    public CatalogUniqueCodeEntityAbsenceException(String entityName, String entityCode, String catalogCode) {
        super("No " + entityName + " with code \"" + StringHelper.escapeHTML(entityCode) + "\n" + " and catalog \"" +
                StringHelper.escapeHTML(catalogCode) +  "\" has been found");
    }
}
