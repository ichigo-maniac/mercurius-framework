package org.mercuriusframework.listeners;

import org.mercuriusframework.entities.AbstractEntity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.UUID;

/**
 * Abstract entity listener
 */
public class AbstractEntityListener {
    /**
     * On "pre-persist" handler
     * @param abstractEntity Abstract entity
     */
    @PrePersist
    private void onPrePersist(AbstractEntity abstractEntity) {
        abstractEntity.setCreationTime(new Date());
        abstractEntity.setModificationTime(new Date());
        if (abstractEntity.getUid() == null) {
            abstractEntity.setUid(UUID.randomUUID().toString().toLowerCase());
        }
    }

    /**
     * On "pre-update" handler
     * @param abstractEntity Abstract entity
     */
    @PreUpdate
    private void onPreUpdate(AbstractEntity abstractEntity) {
        abstractEntity.setModificationTime(new Date());
    }

}
