package org.mercuriusframework.filters;

import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.impl.CurrencyEntityConverter;
import org.mercuriusframework.entities.CurrencyEntity;
import org.mercuriusframework.exceptions.CurrencyAbsenceException;
import org.mercuriusframework.exceptions.StoreAbsenceException;
import org.mercuriusframework.facades.CurrencyFacade;
import org.mercuriusframework.services.ConfigurationService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Default currency setter filter
 */
@Service("defaultCurrencySetterFilter")
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class DefaultCurrencySetterFilter extends OncePerRequestFilter {

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Currency facade
     */
    @Autowired
    @Qualifier("currencyFacade")
    private CurrencyFacade currencyFacade;

    /**
     * Configuration service
     */
    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;

    /**
     * Currency entity converter
     */
    @Autowired
    @Qualifier("currencyEntityConverter")
    private CurrencyEntityConverter currencyEntityConverter;

    /**
     * Do filter internal
     * @param httpServletRequest Http-request
     * @param httpServletResponse Http-response
     * @param filterChain Filter chain
     * @throws ServletException Servlet exception
     * @throws IOException IO exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (currencyFacade.getDefaultCurrency(httpServletRequest) == null) {
            String defaultCurrencyCode = configurationService.getParameter(MercuriusConfigurationParameters.WEB_PARAMETERS.DEFAULT_CURRENCY_CODE);
            /** Load currency */
            if (defaultCurrencyCode != null) {
                CurrencyEntity currencyEntity = uniqueCodeEntityService.getEntityByCode(defaultCurrencyCode, CurrencyEntity.class);
                if (currencyEntity != null) {
                    currencyFacade.setDefaultCurrency(httpServletRequest, currencyEntityConverter.convert(currencyEntity));
                } else {
                    throw new CurrencyAbsenceException(defaultCurrencyCode);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
