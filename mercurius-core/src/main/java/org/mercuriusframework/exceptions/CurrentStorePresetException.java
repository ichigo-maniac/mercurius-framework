package org.mercuriusframework.exceptions;

/**
 * Current store preset exception
 */
public class CurrentStorePresetException extends RuntimeException {

    /**
     * Constructor
     */
    public CurrentStorePresetException() {
        super("The current store hasn't been preset by a Store Facade. Use CurrentStoreSetterFilter filer " +
                "or set the current store manually by Store Facade");
    }
}
