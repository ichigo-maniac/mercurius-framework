package org.mercuriusframework.exceptions;

/**
 * Catalog absence exception
 */
public class CatalogAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param catalogCode Not-existing catalog code
     */
    public CatalogAbsenceException(String catalogCode) {
        super("Catalog with code \"" + catalogCode + "\" doesn't exist");
    }
}
