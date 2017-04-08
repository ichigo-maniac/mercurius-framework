package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.BigStringEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.stereotype.Service;

/**
 * Big string entity solr field converter
 */
@Service("bigStringEntitySolrFieldConverter")
public class BigStringEntitySolrFieldConverter implements SolrFieldConverter {

    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject    Index entity object
     * @return Converted value
     */
    @Override
    public Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject) {
        Object value = getFieldValue(indexObject, solrIndexField.getEntityFieldName());
        if (value != null && value instanceof BigStringEntity) {
            BigStringEntity bigStringEntity = (BigStringEntity) value;
            return bigStringEntity.getValue();
        }
        return null;
    }
}
