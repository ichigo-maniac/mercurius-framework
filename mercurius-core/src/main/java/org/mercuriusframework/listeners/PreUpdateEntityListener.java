package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * "Pre-update" entity listener interface
 */
public interface PreUpdateEntityListener<T extends AbstractEntity> {
    void preUpdate(T entityObject);
}
