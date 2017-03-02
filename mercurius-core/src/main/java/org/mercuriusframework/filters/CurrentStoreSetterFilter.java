package org.mercuriusframework.filters;

import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.impl.StoreEntityConverter;
import org.mercuriusframework.entities.StoreEntity;
import org.mercuriusframework.exceptions.StoreAbsenceException;
import org.mercuriusframework.facades.StoreFacade;
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
 * Http-filter that set to the session a current store
 */
@Service("currentStoreSetterFilter")
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class CurrentStoreSetterFilter extends OncePerRequestFilter {

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    private UniqueCodeEntityService uniqueCodeEntityService;
    /**
     * Configuration service
     */
    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;
    /**
     * Store facade
     */
    @Autowired
    @Qualifier("storeFacade")
    private StoreFacade storeFacade;

    /**
     * Store entity converter
     */
    @Autowired
    @Qualifier("storeEntityConverter")
    private StoreEntityConverter storeEntityConverter;

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
        if (storeFacade.getCurrentStore(httpServletRequest) == null) {
            String currentStoreCode = configurationService.getParameter(MercuriusConfigurationParameters.WEB_PARAMETERS.CURRENT_STORE_CODE);
            /** Load current store catalog */
            if (currentStoreCode != null) {
                StoreEntity storeEntity = uniqueCodeEntityService.getEntityByCode(currentStoreCode, StoreEntity.class);
                if (storeEntity != null) {
                    storeFacade.setCurrentStore(httpServletRequest, storeEntityConverter.convert(storeEntity));
                } else {
                    throw new StoreAbsenceException(currentStoreCode);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
