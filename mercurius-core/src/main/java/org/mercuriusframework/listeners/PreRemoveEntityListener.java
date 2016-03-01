package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Pre-remove" entity listener interface
 */
public interface PreRemoveEntityListener<T extends AbstractEntity> {
    void preRemove(T entityObject);
}
