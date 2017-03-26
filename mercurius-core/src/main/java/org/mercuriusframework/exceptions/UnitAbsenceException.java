package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Unit absence exception
 */
public class UnitAbsenceException extends RuntimeException {

    /**
     * Constructor
     * @param unitCode Not-existing unit code
     */
    public UnitAbsenceException(String unitCode) {
        super("Unit with code \"" + StringHelper.escapeHTML(unitCode) + "\" doesn't exist");
    }

    /**
     * Constructor
     * @param unitCode Not-existing unit code
     * @param catalogCode  Not-existing catalog code
     */
    public UnitAbsenceException(String unitCode, String catalogCode) {
        super("Unit with code \"" + StringHelper.escapeHTML(unitCode) + "\" and catalog \"" + StringHelper.escapeHTML(catalogCode) + "\"doesn't exist");
    }
}
