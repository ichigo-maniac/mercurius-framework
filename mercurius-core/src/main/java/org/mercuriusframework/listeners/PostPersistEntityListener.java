package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Post-persist" entity listener interface
 */
public interface PostPersistEntityListener<T extends AbstractEntity> {
    void postPersist(T entityObject);
}
