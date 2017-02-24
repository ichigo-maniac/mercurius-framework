package org.mercuriusframework.exceptions;

/**
 * Default catalog preset exception. Throws when using methods which get data from default catalog,
 * but the default catalog hasn't been preset by Catalog Facade as default
 */
public class DefaultCatalogPresetException extends RuntimeException {
    /**
     * Constructor
     */
    public DefaultCatalogPresetException() {
        super("The default catalog hasn't been preset by a Catalog Facade. Use DefaultCatalogSetterFilter filer " +
                "or set the default catalog manually by Catalog Facade");
    }
}
