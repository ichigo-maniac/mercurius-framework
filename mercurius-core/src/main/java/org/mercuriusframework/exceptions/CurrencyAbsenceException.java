package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Currency absence exception
 */
public class CurrencyAbsenceException extends RuntimeException {
    /**
     * Constructor
     * @param currencyCode Not-existing currency code
     */
    public CurrencyAbsenceException(String currencyCode) {
        super("Currency with code \"" + StringHelper.escapeHTML(currencyCode) + "\" doesn't exist");
    }
}
