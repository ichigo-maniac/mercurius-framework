package org.mercuriusframework.facades;

import org.mercuriusframework.dto.CurrencyEntityDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Currency facade interface
 */
public interface CurrencyFacade {

    /**
     * Get default currency
     * @return Currency data transfer object
     */
    CurrencyEntityDto getDefaultCurrency();

    /**
     * Get default currency
     * @param request Http-request
     * @return Currency data transfer object
     */
    CurrencyEntityDto getDefaultCurrency(HttpServletRequest request);

    /**
     * Set default currency
     * @param currency Currency data transfer object
     */
    void setDefaultCurrency(CurrencyEntityDto currency);

    /**
     * Set default currency
     * @param request Http-request
     * @param currency Currency data transfer object
     */
    void setDefaultCurrency(HttpServletRequest request, CurrencyEntityDto currency);
}
