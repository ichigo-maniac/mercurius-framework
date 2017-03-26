package org.mercuriusframework.dataimport.exceptions;

/**
 * Unsupported import collection exception
 */
public class UnsupportedImportCollectionException extends RuntimeException {

    /**
     * Constructor
     * @param className Class name
     */
    public UnsupportedImportCollectionException(String className) {
        super("Unsupported data import collection - " + className);
    }
}
