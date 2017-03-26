package org.mercuriusframework.dataimport.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Wrong import data value format exception
 */
public class WrongImportDataValueFormatException extends RuntimeException {

    /**
     * Constructor
     * @param formatTemplate Format template
     */
    public WrongImportDataValueFormatException(String formatTemplate) {
        super("Wrong import data format. Format template - " + StringHelper.escapeHTML(formatTemplate));
    }
}
