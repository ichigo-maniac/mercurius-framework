package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;

/**
 * Base entity listener interface
 */
public interface EntityListener<T extends AbstractEntity> {
    /**
     * Get entity type
     * @return Entity type
     */
    Class<T> getEntityType();
}
