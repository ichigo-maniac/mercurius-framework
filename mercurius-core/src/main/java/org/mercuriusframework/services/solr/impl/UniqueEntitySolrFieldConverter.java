package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.stereotype.Service;

/**
 * Unique entity solr field converter
 */
@Service("uniqueEntitySolrFieldConverter")
public class UniqueEntitySolrFieldConverter implements SolrFieldConverter {

    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject    Index entity object
     * @return Converted value
     */
    @Override
    public Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject) {
        Object value = getFieldValue(indexObject, solrIndexField.getEntityFieldName());
        if (value != null && value instanceof UniqueCodeEntity) {
            return ((UniqueCodeEntity) value).getCode();
        } else {
            return null;
        }
    }
}
