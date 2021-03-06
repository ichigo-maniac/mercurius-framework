package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Pre-persist" entity listener interface
 */
public interface PrePersistEntityListener<T extends AbstractEntity> extends EntityListener<T> {
    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    void prePersist(T entityObject);
}
