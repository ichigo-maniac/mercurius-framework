package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Post-update" entity listener interface
 */
public interface PostUpdateEntityListener<T extends AbstractEntity> {
    void postUpdate(T entityObject);
}
