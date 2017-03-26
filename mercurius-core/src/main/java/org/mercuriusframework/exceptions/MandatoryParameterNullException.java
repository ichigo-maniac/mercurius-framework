package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * Mandatory parameter null exception
 */
public class MandatoryParameterNullException extends RuntimeException {
    /**
     * Constructor
     * @param classType Class type
     * @param paramName Mandatory parameter name
     */
    public MandatoryParameterNullException(Class classType, String paramName) {
        super("Mandatory parameter is null (class type - " + classType.getSimpleName() + ", parameter - " + StringHelper.escapeHTML(paramName) +  ")");
    }
}
