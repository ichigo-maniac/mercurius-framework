package org.mercuriusframework.facades.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.entities.SolrIndexTaskPropertyEntity;
import org.mercuriusframework.facades.SolrIndexFacade;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.mercuriusframework.services.EntityReflectionService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.solr.SolrDocumentFieldsMap;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Solr index facade
 */
@Profile(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)
@Service("solrIndexFacade")
public class SolrIndexFacadeImpl implements SolrIndexFacade {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Constants
     */
    private static final String GET_METHOD_PREFIX = "get";
    private static final String DOCUMENT_ID = "id";

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    protected EntityReflectionService entityReflectionService;

    /**
     * Solr template
     */
    @Autowired
    protected SolrTemplate solrTemplate;

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Index by solr index property
     * @param solrIndexProperty Solr index task property
     */
    @Override
    public void indexBySolrIndexProperty(SolrIndexTaskPropertyEntity solrIndexProperty) {
        /** Load entity objects */
        Class entityClass = entityReflectionService.getEntityClassByEntityName(solrIndexProperty.getIndexEntityName());
        List<AbstractEntity> indexObjects = entityService.getListResultByQuery(solrIndexProperty.getQuery(), entityClass);
        /** Index objects */
        for (AbstractEntity indexObject : indexObjects) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField(DOCUMENT_ID, indexObject.getUuid());
            for (SolrIndexFieldEntity indexField : solrIndexProperty.getIndexFields()) {
                Object fieldValue = null;
                if (indexField.getSolrFieldConverterBeanName() != null) {
                    SolrFieldConverter fieldConverter = ApplicationContextProvider.getBean(
                            indexField.getSolrFieldConverterBeanName(), SolrFieldConverter.class
                    );
                    fieldValue = fieldConverter.convertField(indexField, indexObject);
                } else {
                    fieldValue = getFieldValue(indexObject, indexField.getEntityFieldName());
                }
                if (fieldValue != null && fieldValue instanceof String) {
                    if (indexField.getCaseInsensitive() != null && indexField.getCaseInsensitive()) {
                        fieldValue = ((String) fieldValue).toLowerCase();
                    }
                }
                if (fieldValue instanceof SolrDocumentFieldsMap) {
                    SolrDocumentFieldsMap fieldsMap = (SolrDocumentFieldsMap) fieldValue;
                    for (String solrDocumentField : fieldsMap.keySet()) {
                        solrInputDocument.addField(solrDocumentField, fieldsMap.get(solrDocumentField));
                    }
                } else {
                    solrInputDocument.addField(indexField.getSolrDocumentFieldName(), fieldValue);
                }
            }
            solrTemplate.saveDocument(solrIndexProperty.getSolrCollectionName(), solrInputDocument);
        }
        solrTemplate.softCommit(solrIndexProperty.getSolrCollectionName());
    }

    /**
     * Get field value
     * @param entityObject Entity object
     * @param fieldName Field name
     * @return Field value
     */
    private Object getFieldValue(AbstractEntity entityObject, String fieldName) {
        try {
            Method getterMethod = entityObject.getClass().getMethod(GET_METHOD_PREFIX + StringUtils.capitalize(fieldName));
            return getterMethod.invoke(entityObject);
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return null;
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return null;
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
            LOGGER.error(exception);
            return null;
        }
    }
}
