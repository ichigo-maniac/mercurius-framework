package org.mercuriusframework.exceptions;

/**
 * Currency absence exception
 */
public class CurrencyAbsenceException extends RuntimeException {
    /**
     * Constructor
     * @param currencyCode Not-existing currency code
     */
    public CurrencyAbsenceException(String currencyCode) {
        super("Currency with code \"" + currencyCode + "\" doesn't exist");
    }
}
