package org.mercuriusframework.services.solr.impl;

import org.mercuriusframework.dto.CurrencyEntityDto;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.facades.CurrencyFacade;
import org.mercuriusframework.facades.UnitFacade;
import org.mercuriusframework.services.solr.SolrFieldResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Price solr field resolver
 */
@Service("priceSolrFieldResolver")
public class PriceSolrFieldResolver implements SolrFieldResolver {

    /**
     * Constants
     */
    public static final String PRICE_SOLR_FIELD = "price_";

    /**
     * Currency facade
     */
    @Autowired
    @Qualifier("currencyFacade")
    private CurrencyFacade currencyFacade;

    /**
     * Unit facade
     */
    @Autowired
    @Qualifier("unitFacade")
    private UnitFacade unitFacade;

    /**
     * Resolve field
     * @return Field name
     */
    @Override
    public String resolveField() {
        CurrencyEntityDto currency = currencyFacade.getDefaultCurrency();
        UnitEntityDto unit = unitFacade.getDefaultUnit();
        return PRICE_SOLR_FIELD + unit.getCode() + "_" + currency.getCode();
    }
}
