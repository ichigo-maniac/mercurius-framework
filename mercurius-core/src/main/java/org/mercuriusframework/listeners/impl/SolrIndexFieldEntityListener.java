package org.mercuriusframework.listeners.impl;

import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.exceptions.NoSuchSpecialBeanException;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.mercuriusframework.listeners.PrePersistEntityListener;
import org.mercuriusframework.listeners.PreUpdateEntityListener;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.stereotype.Service;

/**
 * Solr index field entity listener
 */
@Service("solrIndexFieldEntityListener")
public class SolrIndexFieldEntityListener implements PrePersistEntityListener<SolrIndexFieldEntity>, PreUpdateEntityListener<SolrIndexFieldEntity> {

    /**
     * Get entity type
     * @return Entity type
     */
    @Override
    public Class<SolrIndexFieldEntity> getEntityType() {
        return SolrIndexFieldEntity.class;
    }

    /**
     * "Pre-update" handler
     * @param entityObject Entity object
     */
    @Override
    public void preUpdate(SolrIndexFieldEntity entityObject) {
        validateSolrField(entityObject);
    }

    /**
     * "Pre-persist" handler
     * @param entityObject Entity object
     */
    @Override
    public void prePersist(SolrIndexFieldEntity entityObject) {
        validateSolrField(entityObject);
    }

    /**
     * Validate solr index field entity
     * @param entityObject Entity object
     */
    private void validateSolrField(SolrIndexFieldEntity entityObject) {
        try {
            if (entityObject.getSolrFieldConverterBeanName() != null) {
                SolrFieldConverter fieldConverter = (SolrFieldConverter) ApplicationContextProvider.getBean(
                        entityObject.getSolrFieldConverterBeanName(), SolrFieldConverter.class);
                if (fieldConverter == null) {
                    throw new NoSuchSpecialBeanException(entityObject.getSolrFieldConverterBeanName(), SolrFieldConverter.class);
                }
            }
        } catch (Exception exception) {
            throw new NoSuchSpecialBeanException(entityObject.getSolrFieldConverterBeanName(), SolrFieldConverter.class);
        }
    }
}
