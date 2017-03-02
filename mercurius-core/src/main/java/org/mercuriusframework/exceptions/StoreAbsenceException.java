package org.mercuriusframework.exceptions;

/**
 * Store absence exception
 */
public class StoreAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param storeCode Not-existing store code
     */
    public StoreAbsenceException(String storeCode) {
        super("Store with code \"" + storeCode + "\" doesn't exist");
    }
}
