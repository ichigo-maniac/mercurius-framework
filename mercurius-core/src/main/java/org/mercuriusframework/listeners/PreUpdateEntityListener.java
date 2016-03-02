package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Pre-update" entity listener interface
 */
public interface PreUpdateEntityListener<T extends AbstractEntity> extends EntityListener<T> {
    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    void preUpdate(T entityObject);
}
