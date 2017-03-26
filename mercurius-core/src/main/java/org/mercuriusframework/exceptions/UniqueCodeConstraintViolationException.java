package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

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
                "(entity - " + classType.getSimpleName() + ", code - " + StringHelper.escapeHTML(code) + ")");
    }

    /**
     * Constructor
     * @param classType Entity class type
     * @param code Entity unique code
     * @param catalogCode Catalog code
     */
    public UniqueCodeConstraintViolationException(Class classType, String code, String catalogCode) {
        super("Unique code constraint violation exception " +
                "(entity - " + classType.getSimpleName() + ", code - " + StringHelper.escapeHTML(code) + ", catalog code - " +
                StringHelper.escapeHTML(catalogCode) + ")");
    }
}
