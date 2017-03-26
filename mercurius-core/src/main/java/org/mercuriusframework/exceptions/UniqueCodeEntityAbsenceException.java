package org.mercuriusframework.exceptions;

/**
 * Unique code absence exception
 */
public class UniqueCodeEntityAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param entityName Entity name
     * @param entityCode Entity code
     */
    public UniqueCodeEntityAbsenceException(String entityName, String entityCode) {
        super("No " + entityName + " with code \"" + entityCode + "\n" + " has been found");
    }
}
