package org.mercuriusframework.exceptions;

/**
 * Unique code constraint violation exception
 */
public class UniqueCodeConstraintViolationException extends RuntimeException {

    /**
     * Constructor
     * @param classType Entity class type
     * @param code Entity unique code
     */
    public UniqueCodeConstraintViolationException(Class classType, String code) {
        super("Unique code constraint violation exception " +
                "(entity - " + classType.getSimpleName() + ", code - " + code + ")");
    }
}
