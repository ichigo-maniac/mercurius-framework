package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Pre-persist" entity listener interface
 */
public interface PrePersistEntityListener<T extends AbstractEntity> {
    void prePersist(T entityObject);
}
