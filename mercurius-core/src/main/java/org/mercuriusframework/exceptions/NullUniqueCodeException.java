package org.mercuriusframework.exceptions;

/**
 * Null unique code exception
 */
public class NullUniqueCodeException extends RuntimeException {
    /**
     * Constructor Entity class type
     * @param classType
     */
    public NullUniqueCodeException(Class classType) {
        super("Entity unique code null exception (entity type " + classType.getSimpleName() + ")");
    }
}
