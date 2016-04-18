package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.QueryParameter;
import org.mercuriusframework.entities.Catalog;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.CodeGenerationService;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Catalog unique code entity service
 */
@Service("catalogUniqueCodeEntityService")
public class CatalogUniqueCodeEntityServiceImpl implements CatalogUniqueCodeEntityService {
    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;
    /**
     * Code generation service
     */
    @Autowired
    private CodeGenerationService codeGenerationService;

    /**
     * Get catalog unique code entity by code and catalog
     * @param code    Entity code
     * @param catalog Catalog
     * @param clazz   Entity class
     * @return Catalog unique code entity
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> T getEntityByCodeAndCatalog(String code, Catalog catalog, Class<T> clazz) {
        Class classValue = codeGenerationService.getSuperCatalogUniqueCodeClass(clazz);
        return entityService.getSingleResultByQuery("SELECT entity FROM " + classValue.getSimpleName() + " as entity " +
                "WHERE entity.code = :entityCode AND entity.catalog = :catalog", clazz,
                new QueryParameter("entityCode", code), new QueryParameter("catalog", catalog));
    }

    /**
     * Get catalog unique code entity by code and catalog code
     * @param code        Entity code
     * @param catalogCode Catalog code
     * @param clazz       Entity class
     * @return Catalog unique code entity
     */
    @Override
    public <T extends CatalogUniqueCodeEntity> T getEntityByCodeAndCatalogCode(String code, String catalogCode, Class<T> clazz) {
        Class classValue = codeGenerationService.getSuperCatalogUniqueCodeClass(clazz);
        return entityService.getSingleResultByQuery("SELECT entity FROM " + classValue.getSimpleName() + " as entity " +
                        "WHERE entity.code = :entityCode AND entity.catalog.code = :catalogCode", clazz,
                new QueryParameter("entityCode", code), new QueryParameter("catalogCode", catalogCode));
    }
}
