package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.dto.CurrencyEntityDto;
import org.mercuriusframework.facades.CurrencyFacade;
import org.mercuriusframework.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Currency facade
 */
@Service("currencyFacade")
public class CurrencyFacadeImpl implements CurrencyFacade {

    /**
     * Session service
     */
    @Autowired
    @Qualifier("sessionService")
    protected SessionService sessionService;

    /**
     * Get default currency
     * @return Currency data transfer object
     */
    @Override
    public CurrencyEntityDto getDefaultCurrency() {
        return (CurrencyEntityDto) sessionService.getSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CURRENCY);
    }

    /**
     * Get default currency
     * @param request Http-request
     * @return Currency data transfer object
     */
    @Override
    public CurrencyEntityDto getDefaultCurrency(HttpServletRequest request) {
        return (CurrencyEntityDto) sessionService.getSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CURRENCY);
    }

    /**
     * Set default currency
     * @param currency Currency data transfer object
     */
    @Override
    public void setDefaultCurrency(CurrencyEntityDto currency) {
        sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CURRENCY, currency);
    }

    /**
     * Set default currency
     * @param request  Http-request
     * @param currency Currency data transfer object
     */
    @Override
    public void setDefaultCurrency(HttpServletRequest request, CurrencyEntityDto currency) {
        sessionService.setSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_CURRENCY, currency);
    }
}
