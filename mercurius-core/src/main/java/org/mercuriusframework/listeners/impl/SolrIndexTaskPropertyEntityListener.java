package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.SolrIndexTaskPropertyEntity;
import org.mercuriusframework.exceptions.MandatoryParameterNullException;
import org.mercuriusframework.exceptions.NoSuchEntityException;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.services.EntityReflectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Solr index task property entity listener
 */
@Service("solrIndexTaskPropertyEntityListener")
public class SolrIndexTaskPropertyEntityListener implements PrePersistEntityListener<SolrIndexTaskPropertyEntity>,
        PreUpdateEntityListener<SolrIndexTaskPropertyEntity> {

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    protected EntityReflectionService entityReflectionService;

    /**
     * Get entity type
     * @return Entity type
     */
    @Override
    public Class<SolrIndexTaskPropertyEntity> getEntityType() {
        return SolrIndexTaskPropertyEntity.class;
    }

    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(SolrIndexTaskPropertyEntity entityObject) {
        validateIndexProperty(entityObject);
    }

    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(SolrIndexTaskPropertyEntity entityObject) {
        validateIndexProperty(entityObject);
    }

    /**
     * Validate index property
     * @param entityObject Entity object
     */
    private void validateIndexProperty(SolrIndexTaskPropertyEntity entityObject) {
        /** Index entity */
        if (entityObject.getIndexEntityName() == null) {
            throw new MandatoryParameterNullException(SolrIndexTaskPropertyEntity.class, SolrIndexTaskPropertyEntity.INDEX_ENTITY_NAME);
        }
        if (entityReflectionService.getEntityClassByEntityName(entityObject.getIndexEntityName()) == null) {
            throw new NoSuchEntityException(entityObject.getIndexEntityName());
        }
    }
}
