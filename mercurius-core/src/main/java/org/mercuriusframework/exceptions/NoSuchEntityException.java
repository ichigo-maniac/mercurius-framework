package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * No such entity exception
 */
public class NoSuchEntityException extends RuntimeException {

    /**
     * Constructor
     * @param entityName Entity name
     */
    public NoSuchEntityException(String entityName) {
        super("No such entity with name \"" + StringHelper.escapeHTML(entityName) + "\". Check Task entity and Task runner bean");
    }
}
