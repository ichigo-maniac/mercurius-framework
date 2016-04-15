package org.mercuriusframework.exceptions;

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
        super("Mandatory parameter is null (class type " + classType.getSimpleName() + ", parameter" + paramName +  ")");
    }
}
