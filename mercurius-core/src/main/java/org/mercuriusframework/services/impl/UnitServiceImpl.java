package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.exceptions.DefaultCatalogPresetException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UnitService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Unit service
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Catalog facade
     */
    @Autowired
    @Qualifier("catalogFacade")
    protected CatalogFacade catalogFacade;

    /**
     * Get all units (use default catalog)
     * @return List of units
     */
    @Override
    public List<UnitEntity> getAllUnits() {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getAllUnits(catalog.getCode());
    }

    /**
     * Get all units
     * @param catalogCode Catalog code
     * @return List of units
     */
    @Override
    public List<UnitEntity> getAllUnits(String catalogCode) {
        return entityService.getListResultByQuery("SELECT DISTINCT unit FROM " + UnitEntity.ENTITY_NAME + " as unit " +
                "WHERE unit." + UnitEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                UnitEntity.class, new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get all units
     * @param catalog Catalog
     * @return List of units
     */
    @Override
    public List<UnitEntity> getAllUnits(CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT DISTINCT unit FROM " + UnitEntity.ENTITY_NAME + " as unit " +
                        "WHERE unit." + UnitEntity.CATALOG + " = :catalog",
                UnitEntity.class, new QueryParameter("catalog", catalog));
    }

    /**
     * Get units by product
     * @param product Product
     * @return List of units
     */
    @Override
    public List<UnitEntity> getUnitsByProduct(ProductEntity product) {
        return getUnitsByProductUuid(product.getUuid());
    }

    /**
     * Get units by product's uuid
     * @param productUuid Product's uuid
     * @return List of units
     */
    @Override
    public List<UnitEntity> getUnitsByProductUuid(String productUuid) {
        return entityService.getListResultByQuery("SELECT DISTINCT unit FROM " + UnitEntity.ENTITY_NAME + " as unit " +
                        "LEFT JOIN unit." + UnitEntity.PRODUCTS + " as product " +
                        "WHERE product." + ProductEntity.UUID + " = :productUuid",
                UnitEntity.class, new QueryParameter("productUuid", productUuid));
    }

    /**
     * Get units by product's code (use default catalog)
     * @param productCode Product's code
     * @return List of units
     */
    @Override
    public List<UnitEntity> getUnitsByProductCode(String productCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getUnitsByProductCode(productCode, catalog.getCode());
    }

    /**
     * Get units by product's code (use default catalog)
     * @param productCode Product's code
     * @param catalogCode Catalog code
     * @return List of units
     */
    @Override
    public List<UnitEntity> getUnitsByProductCode(String productCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT DISTINCT unit FROM " + UnitEntity.ENTITY_NAME + " as unit " +
                        "LEFT JOIN unit." + UnitEntity.PRODUCTS + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                UnitEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get units by product's code (use default catalog)
     * @param productCode Product's code
     * @param catalog     Catalog
     * @return List of units
     */
    @Override
    public List<UnitEntity> getUnitsByProductCode(String productCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT DISTINCT unit FROM " + UnitEntity.ENTITY_NAME + " as unit " +
                        "LEFT JOIN unit." + UnitEntity.PRODUCTS + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                UnitEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalog", catalog));
    }
}
