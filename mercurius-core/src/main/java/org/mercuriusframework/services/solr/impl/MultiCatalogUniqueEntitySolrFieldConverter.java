package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Multi unique entity solr field converter
 */
@Service("multiCatalogUniqueEntitySolrFieldConverter")
public class MultiCatalogUniqueEntitySolrFieldConverter implements SolrFieldConverter {

    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject    Index entity object
     * @return Converted value
     */
    @Override
    public Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject) {
        Object value = getFieldValue(indexObject, solrIndexField.getEntityFieldName());
        if (value != null && value instanceof Collection) {
            Collection<CatalogUniqueCodeEntity> values = (Collection) value;
            List<String> result = new ArrayList<>(values.size());
            for (CatalogUniqueCodeEntity catalogUniqueCodeEntity : values) {
                result.add(catalogUniqueCodeEntity.getCode());
            }
            return result;
        } else {
            return null;
        }
    }
}
