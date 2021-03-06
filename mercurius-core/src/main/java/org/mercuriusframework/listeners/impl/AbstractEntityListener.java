package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

/**
 * Abstract entity listener
 */
@Service("abstractEntityListener")
public class AbstractEntityListener implements PrePersistEntityListener<AbstractEntity>, PreUpdateEntityListener<AbstractEntity> {
    /**
     * "Pre-persist" handler
     *
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(AbstractEntity entityObject) {
        if (entityObject.getCreationTime() == null) {
            entityObject.setCreationTime(new Date());
        }
        if (entityObject.getModificationTime() == null) {
            entityObject.setModificationTime(new Date());
        }
    }

    /**
     * "Pre-update" handler
     *
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(AbstractEntity entityObject) {
        entityObject.setModificationTime(new Date());
    }

    /**
     * Get entity type
     * @return Entity type
     */
    @Override
    public Class<AbstractEntity> getEntityType() {
        return AbstractEntity.class;
    }

}
