package org.mercuriusframework.tasks;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.solr.common.SolrInputDocument;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.entities.SolrIndexTaskEntity;
import org.mercuriusframework.entities.SolrIndexTaskPropertyEntity;
import org.mercuriusframework.helpers.ApplicationContextProvider;
import org.mercuriusframework.services.AnnotationService;
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
 * Solr index task runner
 */
@Service("solrIndexTaskRunner")
@Profile(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)
public class SolrIndexTaskRunner extends AbstractTaskRunner {

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
     * Solr template
     */
    @Autowired
    protected SolrTemplate solrTemplate;

    /**
     * Annotation service
     */
    @Autowired
    @Qualifier("annotationService")
    protected AnnotationService annotationService;

    /**
     * Task execution (task logic)
     */
    @Override
    protected void taskExecution() {
        /** Load task object */
        SolrIndexTaskEntity solrIndexTaskEntity = entityService.findByUuid(currentTaskUuid, SolrIndexTaskEntity.class);
        SolrIndexTaskPropertyEntity indexProperty = entityService.findByUuid(solrIndexTaskEntity.getIndexProperty().getUuid(),
                SolrIndexTaskPropertyEntity.class, SolrIndexTaskPropertyEntity.INDEX_FIELDS);
        /** Load entity objects */
        Class entityClass = annotationService.getEntityClassByEntityName(indexProperty.getIndexEntityName());
        List<AbstractEntity> indexObjects = entityService.getListResultByQuery(indexProperty.getQuery(), entityClass);
        /** Index objects */
        for (AbstractEntity indexObject : indexObjects) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField(DOCUMENT_ID, indexObject.getUuid());
            for (SolrIndexFieldEntity indexField : indexProperty.getIndexFields()) {
                if (indexField.getSolrFieldConverterBeanName() != null) {
                    SolrFieldConverter fieldConverter = ApplicationContextProvider.getBean(
                        indexField.getSolrFieldConverterBeanName(), SolrFieldConverter.class
                    );
                    solrInputDocument.addField(indexField.getSolrDocumentFieldName(), fieldConverter.convertField(indexField, indexObject));
                } else {
                    solrInputDocument.addField(indexField.getSolrDocumentFieldName(), getFieldValue(indexObject, indexField.getEntityFieldName()));
                }
            }
            solrTemplate.saveDocument(indexProperty.getSolrCollectionName(), solrInputDocument);
        }
        solrTemplate.softCommit(indexProperty.getSolrCollectionName());
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
