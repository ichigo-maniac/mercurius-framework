package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Pre-remove" entity listener interface
 */
public interface PreRemoveEntityListener<T extends AbstractEntity> {
    /**
     * "Pre-remove" handler
     * @param entityObject Entity object
     */
    void preRemove(T entityObject);
}
