package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.CategoryEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.solr.SolrFieldConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Product categories solr field converter
 */
@Service("productCategoriesSolrFieldConverter")
public class ProductCategoriesSolrFieldConverter implements SolrFieldConverter {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Convert field
     * @param solrIndexField Solr index field
     * @param indexObject    Index entity object
     * @return Converted value
     */
    @Override
    public Object convertField(SolrIndexFieldEntity solrIndexField, AbstractEntity indexObject) {
        ProductEntity product = (ProductEntity) indexObject;
        Set<CategoryEntity> categories = new HashSet<>(product.getCategories());
        categories.add(product.getMainCategory());
        /** Check super categories */
        for (CategoryEntity category : categories) {
            CategoryEntity currentCategory = entityService.findByUuid(category.getUuid(), CategoryEntity.class,
                    CategoryEntity.SUPER_CATEGORIES, CategoryEntity.MAIN_SUPER_CATEGORY);
            categories.addAll(getSuperCategories(currentCategory));
        }
        return getCategoriesCodes(categories);
    }

    /**
     * Get super categories
     * @param category Category
     * @return Set of categories
     */
    private Set<CategoryEntity> getSuperCategories(CategoryEntity category) {
        if (category == null) {
            return Collections.emptySet();
        }
        if (category.getSuperCategories().isEmpty() && category.getMainSuperCategory() == null) {
            return Collections.emptySet();
        }
        Set<CategoryEntity> result = new HashSet<>(category.getSuperCategories());
        result.add(category.getMainSuperCategory());
        for (CategoryEntity currentCategory : result) {
            result.addAll(getSuperCategories(entityService.findByUuid(currentCategory.getUuid(), CategoryEntity.class,
                    CategoryEntity.SUPER_CATEGORIES, CategoryEntity.MAIN_SUPER_CATEGORY)));
        }
        return result;
    }

    /**
     * Get categories codes
     * @param categories Categories
     * @return Set of codes
     */
    private Set<String> getCategoriesCodes(Set<CategoryEntity> categories) {
        Set<String> codes = new HashSet<>(categories.size());
        for (CategoryEntity category : categories) {
            codes.add(category.getCode());
        }
        return codes;
    }
}
