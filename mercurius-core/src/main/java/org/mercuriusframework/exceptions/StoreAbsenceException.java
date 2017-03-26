package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Store absence exception
 */
public class StoreAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param storeCode Not-existing store code
     */
    public StoreAbsenceException(String storeCode) {
        super("Store with code \"" + StringHelper.escapeHTML(storeCode) + "\" doesn't exist");
    }
}
