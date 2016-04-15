package org.mercuriusframework.exceptions;

/**
 * Null unique code exception
 */
public class NullUniqueCodeException extends RuntimeException {
    /**
     * Constructor
     * @param classType Entity class type
     */
    public NullUniqueCodeException(Class classType) {
        super("Entity unique code null exception (entity type " + classType.getSimpleName() + ")");
    }
}
