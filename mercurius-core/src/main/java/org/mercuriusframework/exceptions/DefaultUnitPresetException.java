package org.mercuriusframework.exceptions;

/**
 * Default unit preset exception. Throws when using methods which get data from default unit,
 * but the default unit hasn't been preset by Unit Facade as default
 */
public class DefaultUnitPresetException extends RuntimeException {
    /**
     * Constructor
     */
    public DefaultUnitPresetException() {
        super("The default unit hasn't been preset by a Unit Facade. Use DefaultUnitSetterFilter filer " +
                "or set the default unit manually by Unit Facade");
    }
}
