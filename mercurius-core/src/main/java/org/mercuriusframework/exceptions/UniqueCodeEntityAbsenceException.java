package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

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
        super("No " + StringHelper.escapeHTML(entityName) + " with code \"" + StringHelper.escapeHTML(entityCode) + "\n" + " has been found");
    }
}
