package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.PriceEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.services.PriceService;
import org.mercuriusframework.services.solr.SolrDocumentFieldsMap;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product price solr field converter
 */
@Service("productPriceSolrFieldConverter")
public class ProductPriceSolrFieldConverter implements SolrFieldConverter {

    /**
     * Price prefix
     */
    private static final String PRICE_PREFIX = "price_";

    /**
     * Price service
     */
    @Autowired
    @Qualifier("priceService")
    protected PriceService priceService;

    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject    Index entity object
     * @return Converted value
     */
    @Override
    public Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject) {
        ProductEntity product = (ProductEntity) indexObject;
        SolrDocumentFieldsMap fieldsMap = new SolrDocumentFieldsMap();
        List<PriceEntity> prices = priceService.getPricesByProductUuid(product.getUuid());
        for (PriceEntity priceEntity : prices) {
            fieldsMap.put(PRICE_PREFIX + priceEntity.getUnit().getCode() + "_" + priceEntity.getCurrency().getCode(), priceEntity.getPriceValue());
        }
        return fieldsMap;
    }
}
