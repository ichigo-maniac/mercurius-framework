package org.mercuriusframework.fillers.impl;

import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.converters.impl.CategoryEntityConverter;
import org.mercuriusframework.converters.impl.PriceEntityConverter;
import org.mercuriusframework.converters.impl.StockEntityConverter;
import org.mercuriusframework.dto.*;
import org.mercuriusframework.entities.*;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.enums.ProductLoadOptions;
import org.mercuriusframework.enums.StockLoadOptions;
import org.mercuriusframework.exceptions.CurrentStorePresetException;
import org.mercuriusframework.exceptions.DefaultUnitPresetException;
import org.mercuriusframework.facades.CurrencyFacade;
import org.mercuriusframework.facades.StoreFacade;
import org.mercuriusframework.facades.UnitFacade;
import org.mercuriusframework.services.CategoryService;
import org.mercuriusframework.services.PriceService;
import org.mercuriusframework.services.StockService;
import org.mercuriusframework.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Product entity filler
 */
@Service("productEntityFiller")
public class ProductEntityFiller extends CatalogUniqueCodeEntityFiller<ProductEntity, ProductEntityDto> {

    /**
     * Category service
     */
    @Autowired
    @Qualifier("categoryService")
    protected CategoryService categoryService;

    /**
     * Category entity converter
     */
    @Autowired
    @Qualifier("categoryEntityConverter")
    protected CategoryEntityConverter categoryEntityConverter;

    /**
     * Stock service
     */
    @Autowired
    @Qualifier("stockService")
    protected StockService stockService;

    /**
     * Stock entity converter
     */
    @Autowired
    @Qualifier("stockEntityConverter")
    protected StockEntityConverter stockEntityConverter;

    /**
     * Unit facade
     */
    @Autowired
    @Qualifier("unitFacade")
    protected UnitFacade unitFacade;

    /**
     * Store facade
     */
    @Autowired
    @Qualifier("storeFacade")
    protected StoreFacade storeFacade;

    /**
     * Warehouse service
     */
    @Autowired
    @Qualifier("warehouseService")
    protected WarehouseService warehouseService;

    /**
     * Currency facade
     */
    @Autowired
    @Qualifier("currencyFacade")
    protected CurrencyFacade currencyFacade;

    /**
     * Price service
     */
    @Autowired
    @Qualifier("priceService")
    protected PriceService priceService;

    /**
     * Price entity converter
     */
    @Autowired
    @Qualifier("priceEntityConverter")
    protected PriceEntityConverter priceEntityConverter;

    /**
     * Fill a result object from a source object
     * @param productEntity    Source object
     * @param productEntityDto Result object
     * @param options          Load options
     */
    @Override
    public void fillIn(ProductEntity productEntity, ProductEntityDto productEntityDto, LoadOptions... options) {
        super.fillIn(productEntity, productEntityDto, options);
        productEntityDto.setShortName(productEntity.getShortName());
        /** Description */
        if (ArrayUtils.contains(options, ProductLoadOptions.DESCRIPTION)) {
            productEntityDto.setDescription(productEntity.getDescription() != null ? productEntity.getDescription().getValue() : "");
        }
        /** Breadcrumbs */
        if (ArrayUtils.contains(options, ProductLoadOptions.BREAD_CRUMBS)) {
            List<CategoryEntity> breadcrumbs = categoryService.getBreadCrumbsByCategoryUuid(productEntity.getMainCategory().getUuid());
            productEntityDto.setBreadCrumbs(categoryEntityConverter.convertAll(breadcrumbs));
        }
        /** Prices */
        if (ArrayUtils.contains(options, ProductLoadOptions.ALL_PRICES)) {
            productEntityDto.setPrices(priceEntityConverter.convertAll(priceService.getPricesByProductUuid(productEntity.getUuid())));
        }
        if (ArrayUtils.contains(options, ProductLoadOptions.DEFAULT_CURRENCY_PRICES)) {
            productEntityDto.setPrices(priceEntityConverter.convertAll(priceService.getPricesByProductCodeAndCurrencyCode(
                    productEntity.getCode(), currencyFacade.getDefaultCurrency().getCode())));
        }
        if (ArrayUtils.contains(options, ProductLoadOptions.DEFAULT_UNIT_PRICES)) {
            productEntityDto.setPrices(priceEntityConverter.convertAll(priceService.getPricesByProductUuidAndDefaultUnit(productEntity.getUuid())));
        }
        if (ArrayUtils.contains(options, ProductLoadOptions.DEFAULT_CURRENCY_AND_UNIT_PRICE)) {
            List<PriceEntity> prices = priceService.getPricesByProductUuidAndUnitUuidAndCurrencyUuid(productEntity.getUuid(), unitFacade.getDefaultUnit().getUuid(),
                    currencyFacade.getDefaultCurrency().getUuid());
            productEntityDto.setPrice(prices.isEmpty() ? null : priceEntityConverter.convert(prices.get(0)));
        }
        /** Stocks */
        if (ArrayUtils.contains(options, ProductLoadOptions.ALL_STOCKS)) {
            setAllStocks(productEntity, productEntityDto);
        }
        if (ArrayUtils.contains(options, ProductLoadOptions.ALL_STOCKS_FOR_SET_STORE)) {
            setAllStocksForSetStore(productEntity, productEntityDto);
        }
        if (ArrayUtils.contains(options, ProductLoadOptions.DEFAULT_UNIT_STOCKS)) {
            setStocksForDefaultUnit(productEntity, productEntityDto);
        }
        if (ArrayUtils.contains(options, ProductLoadOptions.DEFAULT_UNIT_STOCKS_FOR_SET_STORE)) {
            setStocksForDefaultUnitAndSetStore(productEntity, productEntityDto);
        }
    }

    /**
     * Set all stocks
     * @param productEntity Product entity
     * @param productEntityDto Product entity data transfer object
     */
    private void setAllStocks(ProductEntity productEntity, ProductEntityDto productEntityDto) {
        List<StockEntity> stockEntities = stockService.getStocksByProductUuid(productEntity.getUuid(), true);
        setStocks(productEntity, productEntityDto, stockEntities);
    }

    /**
     * Set all stocks
     * @param productEntity Product entity
     * @param productEntityDto Product entity data transfer object
     */
    private void setAllStocksForSetStore(ProductEntity productEntity, ProductEntityDto productEntityDto) {
        StoreEntityDto store = storeFacade.getCurrentStore();
        if (store == null) {
            throw new CurrentStorePresetException();
        }
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode(store.getCode(), true);
        List<StockEntity> stockEntities = stockService.getStocksByProductUuidAndWarehouses(productEntity.getUuid(), warehouses);
        setStocks(productEntity, productEntityDto, stockEntities);
    }

    /**
     * Set all stocks
     * @param productEntity Product entity
     * @param productEntityDto Product entity data transfer object
     */
    private void setStocksForDefaultUnitAndSetStore(ProductEntity productEntity, ProductEntityDto productEntityDto) {
        UnitEntityDto defaultUnit = unitFacade.getDefaultUnit();
        if (defaultUnit == null) {
            throw new DefaultUnitPresetException();
        }
        StoreEntityDto store = storeFacade.getCurrentStore();
        if (store == null) {
            throw new CurrentStorePresetException();
        }
        List<WarehouseEntity> warehouses = warehouseService.getWarehousesByStoreCode(store.getCode(), true);
        List<StockEntity> stockEntities = stockService.getStocksByProductAndUnitUuidAndWarehouses(
                productEntity.getUuid(), defaultUnit.getUuid(), warehouses);
        List<StockEntityDto> stockDtos = stockEntityConverter.convertAll(stockEntities, StockLoadOptions.UNIT);
        productEntityDto.setStocks(stockDtos);
        /** Create default stock total */
        StockTotalDto defaultStockTotal = new StockTotalDto(defaultUnit, stockDtos);
        Map<UnitEntityDto, StockTotalDto> stockMap = new HashMap<>();
        stockMap.put(defaultUnit, defaultStockTotal);
        /** Set stock map and default stock total */
        productEntityDto.setStocksMap(stockMap);
        productEntityDto.setDefaultStock(defaultStockTotal);
    }

    /**
     * Set stocks
     * @param productEntity Product entity
     * @param productEntityDto Product entity data transfer object
     * @param stockEntities Stock entities
     */
    private void setStocks(ProductEntity productEntity, ProductEntityDto productEntityDto, List<StockEntity> stockEntities) {
        List<StockEntityDto> stockDtos = stockEntityConverter.convertAll(stockEntities, StockLoadOptions.UNIT);
        Set<UnitEntityDto> units = new HashSet<>();
        for (StockEntityDto stockEntityDto : stockDtos) {
            units.add(stockEntityDto.getUnit());
        }
        productEntityDto.setStocks(stockDtos);
        /** Create stocks map */
        Map<UnitEntityDto, StockTotalDto> stockMap = new HashMap<>();
        for (UnitEntityDto unit : units) {
            List<StockEntityDto> stockEntityDtos = new ArrayList<>();
            for (StockEntityDto stockEntityDto : stockDtos) {
                if (stockEntityDto.getUnit().equals(unit)) {
                    stockEntityDtos.add(stockEntityDto);
                }
            }
            stockMap.put(unit, new StockTotalDto(unit, stockEntityDtos));
        }
        productEntityDto.setStocksMap(stockMap);
    }

    /**
     * Set all stocks
     * @param productEntity Product entity
     * @param productEntityDto Product entity data transfer object
     */
    private void setStocksForDefaultUnit(ProductEntity productEntity, ProductEntityDto productEntityDto) {
        final UnitEntityDto defaultUnit = unitFacade.getDefaultUnit();
        if (defaultUnit == null) {
            throw new DefaultUnitPresetException();
        }
        List<StockEntity> stockEntities = stockService.getStocksByProductAndUnitUuid(productEntity.getUuid(), defaultUnit.getUuid(), true);
        List<StockEntityDto> stockDtos = stockEntityConverter.convertAll(stockEntities, StockLoadOptions.UNIT);
        productEntityDto.setStocks(stockDtos);
        /** Create default stock total */
        StockTotalDto defaultStockTotal = new StockTotalDto(defaultUnit, stockDtos);
        Map<UnitEntityDto, StockTotalDto> stockMap = new HashMap<>();
        stockMap.put(defaultUnit, defaultStockTotal);
        /** Set stock map and default stock total */
        productEntityDto.setStocksMap(stockMap);
        productEntityDto.setDefaultStock(defaultStockTotal);
    }
}
