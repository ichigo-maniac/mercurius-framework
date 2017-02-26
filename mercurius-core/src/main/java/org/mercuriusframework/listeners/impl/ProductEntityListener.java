package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.BigStringEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.springframework.stereotype.Service;

/**
 * Product entity listener
 */
@Service("productEntityListener")
public class ProductEntityListener implements PreUpdateEntityListener<ProductEntity>, PrePersistEntityListener<ProductEntity> {
    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(ProductEntity entityObject) {
        if (entityObject.getDescription() == null) {
            BigStringEntity description = new BigStringEntity("", entityObject.getName() + " description", entityObject.getCatalog());
            entityObject.setDescription(description);
        }
    }

    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(ProductEntity entityObject) {
        if (entityObject.getDescription() == null) {
            BigStringEntity description = new BigStringEntity("", entityObject.getName() + " description", entityObject.getCatalog());
            entityObject.setDescription(description);
        }
    }

    /**
     * Get entity type
     * @return Entity type
     */
    @Override
    public Class<ProductEntity> getEntityType() {
        return ProductEntity.class;
    }
}
