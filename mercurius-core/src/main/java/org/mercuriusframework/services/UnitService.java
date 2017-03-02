package org.mercuriusframework.services;

import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.entities.UnitEntity;

import java.util.List;

/**
 * Unit service interface
 */
public interface UnitService {
    /**
     * Get all units (use default catalog)
     * @return List of units
     */
    List<UnitEntity> getAllUnits();

    /**
     * Get all units
     * @param catalogCode Catalog code
     * @return List of units
     */
    List<UnitEntity> getAllUnits(String catalogCode);

    /**
     * Get all units
     * @param catalog Catalog
     * @return List of units
     */
    List<UnitEntity> getAllUnits(CatalogEntity catalog);

    /**
     * Get units by product
     * @param product Product
     * @return List of units
     */
    List<UnitEntity> getUnitsByProduct(ProductEntity product);

    /**
     * Get units by product's uuid
     * @param productUuid Product's uuid
     * @return List of units
     */
    List<UnitEntity> getUnitsByProductUuid(String productUuid);

    /**
     * Get units by product's code (use default catalog)
     * @param productCode Product's code
     * @return List of units
     */
    List<UnitEntity> getUnitsByProductCode(String productCode);

    /**
     * Get units by product's code (use default catalog)
     * @param productCode Product's code
     * @param catalogCode Catalog code
     * @return List of units
     */
    List<UnitEntity> getUnitsByProductCode(String productCode, String catalogCode);

    /**
     * Get units by product's code (use default catalog)
     * @param productCode Product's code
     * @param catalog Catalog
     * @return List of units
     */
    List<UnitEntity> getUnitsByProductCode(String productCode, CatalogEntity catalog);

}
