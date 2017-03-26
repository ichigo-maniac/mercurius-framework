package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Catalog absence exception
 */
public class CatalogAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param catalogCode Not-existing catalog code
     */
    public CatalogAbsenceException(String catalogCode) {
        super("Catalog with code \"" + StringHelper.escapeHTML(catalogCode) + "\" doesn't exist");
    }
}
