package org.mercuriusframework.services.impl;

import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.entities.*;
import org.mercuriusframework.exceptions.DefaultCatalogPresetException;
import org.mercuriusframework.exceptions.DefaultUnitPresetException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.facades.UnitFacade;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.PriceService;
import org.mercuriusframework.services.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Price service
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Catalog unique code entity service
     */
    @Autowired
    @Qualifier("catalogUniqueCodeEntityService")
    protected CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Catalog facade
     */
    @Autowired
    @Qualifier("catalogFacade")
    protected CatalogFacade catalogFacade;

    /**
     * Unit facade
     */
    @Autowired
    @Qualifier("unitFacade")
    protected UnitFacade unitFacade;

    /**
     * Get prices by product uuid
     * @param productUuid Product uuid
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductUuid(String productUuid) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                "WHERE price." + PriceEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid",
                PriceEntity.class, new QueryParameter("productUuid", productUuid));
    }

    /**
     * Get prices by product
     * @param product Product
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProduct(ProductEntity product) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + " = :product",
                PriceEntity.class, new QueryParameter("product", product));
    }

    /**
     * Get prices by product code (use default catalog)
     * @param productCode Product code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCode(String productCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getPricesByProductCode(productCode, catalog.getCode());
    }

    /**
     * Get prices by product code and catalog code
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCode(String productCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                PriceEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get prices by product code and catalog
     * @param productCode Product code
     * @param catalog Catalog
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCode(String productCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                PriceEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("catalog", catalog));
    }

    /**
     * Get prices by product uuid and currency uuid
     * @param productUuid  Product uuid
     * @param currencyUuid Currency uuid
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductUuidAndCurrencyUuid(String productUuid, String currencyUuid) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND price." + PriceEntity.CURRENCY + "." + CurrencyEntity.UUID + " = :currencyUuid",
                PriceEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("currencyUuid", currencyUuid));
    }

    /**
     * Get prices by product and currency
     * @param product  Product
     * @param currency Currency
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductAndCurrency(ProductEntity product, CurrencyEntity currency) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + " = :product " +
                        "AND price." + PriceEntity.CURRENCY + " = :currency",
                PriceEntity.class, new QueryParameter("product", product), new QueryParameter("currency", currency));
    }

    /**
     * Get prices by product code and currency code (use default catalog)
     * @param productCode  Product code
     * @param currencyCode Currency code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndCurrencyCode(String productCode, String currencyCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getPricesByProductCodeAndCurrencyCode(productCode, currencyCode, catalog.getCode());
    }

    /**
     * Get prices by product code and currency code
     * @param productCode  Product code
     * @param currencyCode Currency code
     * @param catalogCode  Catalog code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndCurrencyCode(String productCode, String currencyCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND price." + PriceEntity.CURRENCY + "." + CurrencyEntity.CODE + " = :currencyCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                PriceEntity.class, new QueryParameter("productCode", productCode),
                new QueryParameter("catalogCode", catalogCode), new QueryParameter("currencyCode", currencyCode));
    }

    /**
     * Get prices by product code and currency code
     *
     * @param productCode  Product code
     * @param currencyCode Currency code
     * @param catalog      Catalog
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndCurrencyCode(String productCode, String currencyCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND price." + PriceEntity.CURRENCY + "." + CurrencyEntity.CODE + " = :currencyCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                PriceEntity.class, new QueryParameter("productCode", productCode),
                new QueryParameter("catalog", catalog), new QueryParameter("currencyCode", currencyCode));
    }

    /**
     * Get prices by product uuid and unit uuid
     * @param productUuid Product uuid
     * @param unitUuid    Unit uuid
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductUuidAndUnitUuid(String productUuid, String unitUuid) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND price." + PriceEntity.UNIT + "." + PriceEntity.UUID + " = :unitUuid",
                PriceEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("unitUuid", unitUuid));
    }

    /**
     * Get prices by product and unit
     * @param product Product
     * @param unit    Unit
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductAndUnit(ProductEntity product, UnitEntity unit) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + " = :product " +
                        "AND price." + PriceEntity.UNIT + " = :unit",
                PriceEntity.class, new QueryParameter("product", product), new QueryParameter("unit", unit));
    }

    /**
     * Get prices by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode    Unit code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndUnitCode(String productCode, String unitCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getPricesByProductCodeAndUnitCode(productCode, unitCode, catalog.getCode());
    }

    /**
     * Get prices by product code and unit code and catalog code
     * @param productCode Product code
     * @param unitCode    Unit code
     * @param catalogCode Catalog code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndUnitCode(String productCode, String unitCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND price." + PriceEntity.UNIT + "." + UnitEntity.CODE + " = :unitCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                PriceEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("unitCode", unitCode), new QueryParameter("catalogCode", catalogCode));
    }

    /**
     * Get prices by product code and unit code and catalog
     * @param productCode Product code
     * @param unitCode    Unit code
     * @param catalog     Catalog
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndUnitCode(String productCode, String unitCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND price." + PriceEntity.UNIT + "." + UnitEntity.CODE + " = :unitCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                PriceEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("unitCode", unitCode), new QueryParameter("catalog", catalog));
    }

    /**
     * Get prices by product uuid and default set unit
     * @param productUuid Product uuid
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductUuidAndDefaultUnit(String productUuid) {
        final UnitEntityDto defaultUnit = unitFacade.getDefaultUnit();
        if (defaultUnit == null) {
            throw new DefaultUnitPresetException();
        }
        return getPricesByProductUuidAndUnitUuid(productUuid, defaultUnit.getUuid());
    }

    /**
     * Get prices by product and default set unit
     * @param product Product
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductAndDefaultUnit(ProductEntity product) {
        final UnitEntityDto defaultUnit = unitFacade.getDefaultUnit();
        if (defaultUnit == null) {
            throw new DefaultUnitPresetException();
        }
        return getPricesByProductUuidAndUnitUuid(product.getUuid(), defaultUnit.getUuid());
    }

    /**
     * Get prices by product code and default set unit
     * @param productCode Product code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndDefaultUnit(String productCode) {
        final UnitEntityDto defaultUnit = unitFacade.getDefaultUnit();
        if (defaultUnit == null) {
            throw new DefaultUnitPresetException();
        }
        return getPricesByProductCodeAndUnitCode(productCode, defaultUnit.getCode(), defaultUnit.getCatalog().getCode());
    }

    /**
     * Get prices by product uuid and unit uuid and currency uuid
     *
     * @param productUuid  Product uuid
     * @param unitUuid     Unit uuid
     * @param currencyUuid Currency
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductUuidAndUnitUuidAndCurrencyUuid(String productUuid, String unitUuid, String currencyUuid) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + "." + ProductEntity.UUID + " = :productUuid " +
                        "AND price." + PriceEntity.UNIT + "." + UnitEntity.UUID + " = :unitUuid " +
                        "AND price." + PriceEntity.CURRENCY + "." + CurrencyEntity.UUID + " = :currencyUuid",
                PriceEntity.class, new QueryParameter("productUuid", productUuid), new QueryParameter("unitUuid", unitUuid),
                new QueryParameter("currencyUuid", currencyUuid));
    }

    /**
     * Get prices by product and unit and currency
     *
     * @param product  Product
     * @param unit     Unit
     * @param currency Currency
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductAndUnitAndCurrency(ProductEntity product, UnitEntity unit, CurrencyEntity currency) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "WHERE price." + PriceEntity.PRODUCT + " = :product " +
                        "AND price." + PriceEntity.UNIT + " = :unit " +
                        "AND price." + PriceEntity.CURRENCY + " = :currency",
                PriceEntity.class, new QueryParameter("product", product), new QueryParameter("unit", unit),
                new QueryParameter("currency", currency));
    }

    /**
     * Get prices by product code and unit code and currency code
     *
     * @param productCode  Product code
     * @param unitCode     Unit code
     * @param currencyCode Currency code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndUnitCodeAndCurrencyCode(String productCode, String unitCode, String currencyCode) {
        CatalogEntityDto catalog = catalogFacade.getDefaultCatalog();
        if (catalog == null) {
            throw new DefaultCatalogPresetException();
        }
        return getPricesByProductCodeAndUnitCodeAndCurrencyCode(productCode, unitCode, currencyCode, catalog.getCode());
    }

    /**
     * Get prices by product and unit and currency
     *
     * @param productCode  Product code
     * @param unitCode     Unit code
     * @param currencyCode Currency code
     * @param catalogCode  Catalog code
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndUnitCodeAndCurrencyCode(String productCode, String unitCode, String currencyCode, String catalogCode) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND price." + PriceEntity.UNIT + "." + UnitEntity.CODE + " = :unitCode " +
                        "AND price." + PriceEntity.CURRENCY + "." + CurrencyEntity.CODE + " = :currencyCode " +
                        "AND product." + ProductEntity.CATALOG + "." + CatalogEntity.CODE + " = :catalogCode",
                PriceEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("unitCode", unitCode),
                new QueryParameter("catalogCode", catalogCode), new QueryParameter("currencyCode", currencyCode));
    }

    /**
     * Get prices by product and unit and currency
     *
     * @param productCode  Product code
     * @param unitCode     Unit code
     * @param currencyCode Currency code
     * @param catalog      Catalog
     * @return List of prices
     */
    @Override
    public List<PriceEntity> getPricesByProductCodeAndUnitCodeAndCurrencyCode(String productCode, String unitCode, String currencyCode, CatalogEntity catalog) {
        return entityService.getListResultByQuery("SELECT price FROM " + PriceEntity.ENTITY_NAME + " as price " +
                        "LEFT JOIN price." + PriceEntity.PRODUCT + " as product " +
                        "WHERE product." + ProductEntity.CODE + " = :productCode " +
                        "AND price." + PriceEntity.UNIT + "." + UnitEntity.CODE + " = :unitCode " +
                        "AND price." + PriceEntity.CURRENCY + "." + CurrencyEntity.CODE + " = :currencyCode " +
                        "AND product." + ProductEntity.CATALOG + " = :catalog",
                PriceEntity.class, new QueryParameter("productCode", productCode), new QueryParameter("unitCode", unitCode),
                new QueryParameter("catalog", catalog), new QueryParameter("currencyCode", currencyCode));
    }
}
