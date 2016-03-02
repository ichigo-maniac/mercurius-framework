package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Post-load" entity listener interface
 */
public interface PostLoadEntityListener<T extends AbstractEntity> extends EntityListener<T> {
    /**
     * "Post-load" handler
     * @param entityObject Entity object
     */
    void postLoad(T entityObject);
}
