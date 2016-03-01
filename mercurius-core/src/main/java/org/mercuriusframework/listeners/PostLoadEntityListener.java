package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Post-load" entity listener interface
 */
public interface PostLoadEntityListener<T extends AbstractEntity> {
    void postLoad(T entityObject);
}
