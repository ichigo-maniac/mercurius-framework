package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.entities.*;
import org.mercuriusframework.enums.FeatureType;
import org.mercuriusframework.services.solr.SolrDocumentFieldsMap;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Feature value solr field converter
 */
@Service("featureValuesSolrFieldConverter")
public class FeatureValuesSolrFieldConverter implements SolrFieldConverter {

    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject    Index entity object
     * @return Converted value
     */
    @Override
    public Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject) {
        List<FeatureValueEntity> featureValues = (List<FeatureValueEntity>) getFieldValue(indexObject, solrIndexField.getEntityFieldName());
        SolrDocumentFieldsMap fieldsMap = new SolrDocumentFieldsMap();
        for (FeatureValueEntity featureValue : featureValues) {
            FeatureEntity feature = featureValue.getFeature();
            if (feature.getIncludeInIndex() != null && feature.getIncludeInIndex()) {
                if (feature.getSolrDocumentFieldName() != null) {
                    if (feature.getFeatureType() == FeatureType.DICTIONARY_TYPE) {
                        fieldsMap.put(feature.getSolrDocumentFieldName(),
                                ((DictionaryItemEntity) featureValue.getValue().getValue()).getCode());
                    } else {
                        fieldsMap.put(feature.getSolrDocumentFieldName(), featureValue.getValue().getValue());
                    }
                }
            }
        }
        return fieldsMap;
    }
}
