package org.mercuriusframework.exceptions;

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
        super("No " + entityName + " with code \"" + entityCode + "\n" + " and catalog \"" +
                catalogCode +  "\" has been found");
    }
}
