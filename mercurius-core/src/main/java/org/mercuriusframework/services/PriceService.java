package org.mercuriusframework.services;

import org.mercuriusframework.entities.*;

import java.util.List;

/**
 * Price service interface
 */
public interface PriceService {

    /**
     * Get prices by product uuid
     * @param productUuid Product uuid
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductUuid(String productUuid);

    /**
     * Get prices by product
     * @param product Product
     * @return List of prices
     */
    List<PriceEntity> getPricesByProduct(ProductEntity product);

    /**
     * Get prices by product code (use default catalog)
     * @param productCode Product code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCode(String productCode);

    /**
     * Get prices by product code and catalog code
     * @param productCode Product code
     * @param catalogCode Catalog code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCode(String productCode, String catalogCode);

    /**
     * Get prices by product code and catalog
     * @param productCode Product code
     * @param catalog Catalog
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCode(String productCode, CatalogEntity catalog);

    /**
     * Get prices by product uuid and currency uuid
     * @param productUuid Product uuid
     * @param currencyUuid Currency uuid
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductUuidAndCurrencyUuid(String productUuid, String currencyUuid);

    /**
     * Get prices by product and currency
     * @param product Product
     * @param currency Currency
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductAndCurrency(ProductEntity product, CurrencyEntity currency);

    /**
     * Get prices by product code and currency code (use default catalog)
     * @param productCode Product code
     * @param currencyCode Currency code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndCurrencyCode(String productCode, String currencyCode);

    /**
     * Get prices by product code and currency code
     * @param productCode Product code
     * @param currencyCode Currency code
     * @param catalogCode Catalog code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndCurrencyCode(String productCode, String currencyCode, String catalogCode);

    /**
     * Get prices by product code and currency code
     * @param productCode Product code
     * @param currencyCode Currency code
     * @param catalog Catalog
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndCurrencyCode(String productCode, String currencyCode, CatalogEntity catalog);

    /**
     * Get prices by product uuid and unit uuid
     * @param productUuid Product uuid
     * @param unitUuid Unit uuid
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductUuidAndUnitUuid(String productUuid, String unitUuid);

    /**
     * Get prices by product and unit
     * @param product Product
     * @param unit Unit
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductAndUnit(ProductEntity product, UnitEntity unit);

    /**
     * Get prices by product code and unit code (use default catalog)
     * @param productCode Product code
     * @param unitCode Unit code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndUnitCode(String productCode, String unitCode);

    /**
     * Get prices by product code and unit code and catalog code
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalogCode Catalog code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndUnitCode(String productCode, String unitCode, String catalogCode);

    /**
     * Get prices by product code and unit code and catalog
     * @param productCode Product code
     * @param unitCode Unit code
     * @param catalog Catalog
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndUnitCode(String productCode, String unitCode, CatalogEntity catalog);

    /**
     * Get prices by product uuid and default set unit
     * @param productUuid Product uuid
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductUuidAndDefaultUnit(String productUuid);

    /**
     * Get prices by product and default set unit
     * @param product Product
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductAndDefaultUnit(ProductEntity product);

    /**
     * Get prices by product code and default set unit
     * @param productCode Product code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndDefaultUnit(String productCode);

    /**
     * Get prices by product uuid and unit uuid and currency uuid
     * @param productUuid Product uuid
     * @param unitUuid Unit uuid
     * @param currencyUuid Currency
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductUuidAndUnitUuidAndCurrencyUuid(String productUuid, String unitUuid, String currencyUuid);

    /**
     * Get prices by product and unit and currency
     * @param product Product
     * @param unit Unit
     * @param currency Currency
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductAndUnitAndCurrency(ProductEntity product, UnitEntity unit, CurrencyEntity currency);

    /**
     * Get prices by product code and unit code and currency code
     * @param productCode Product code
     * @param unitCode Unit code
     * @param currencyCode Currency code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndUnitCodeAndCurrencyCode(String productCode, String unitCode, String currencyCode);

    /**
     * Get prices by product and unit and currency
     * @param productCode Product code
     * @param unitCode Unit code
     * @param currencyCode Currency code
     * @param catalogCode Catalog code
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndUnitCodeAndCurrencyCode(String productCode, String unitCode, String currencyCode, String catalogCode);

    /**
     * Get prices by product and unit and currency
     * @param productCode Product code
     * @param unitCode Unit code
     * @param currencyCode Currency code
     * @param catalog Catalog
     * @return List of prices
     */
    List<PriceEntity> getPricesByProductCodeAndUnitCodeAndCurrencyCode(String productCode, String unitCode, String currencyCode, CatalogEntity catalog);
}
