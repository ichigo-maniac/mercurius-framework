package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Post-remove" entity listener interface
 */
public interface PostRemoveEntityListener<T extends AbstractEntity> {
    void postRemove(T entityObject);
}
