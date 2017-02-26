package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.BigStringEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.springframework.stereotype.Service;

/**
 * Category entity listener
 */
@Service("categoryEntityListener")
public class CategoryEntityListener implements PreUpdateEntityListener<CategoryEntity>, PrePersistEntityListener<CategoryEntity> {
    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    public void prePersist(CategoryEntity entityObject) {
        if (entityObject.getDescription() == null) {
            BigStringEntity description = new BigStringEntity("", entityObject.getName() + " description", entityObject.getCatalog());
            entityObject.setDescription(description);
        }
    }

    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    public void preUpdate(CategoryEntity entityObject) {
        if (entityObject.getDescription() == null) {
            BigStringEntity description = new BigStringEntity("", entityObject.getName() + " description", entityObject.getCatalog());
            entityObject.setDescription(description);
        }
    }

    /**
     * Get entity type
     *
     * @return Entity type
     */
    public Class<CategoryEntity> getEntityType() {
        return CategoryEntity.class;
    }
}
