package org.mercuriusframework.filters;

import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.impl.CatalogEntityConverter;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.exceptions.CatalogAbsenceException;
import org.mercuriusframework.facades.CatalogFacade;
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
 * Http-filter that set to the session a default catalog for the whole web-application
 */
@Service("defaultCatalogSetterFilter")
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class DefaultCatalogSetterFilter extends OncePerRequestFilter {

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    protected UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Configuration service
     */
    @Autowired
    @Qualifier("configurationService")
    protected ConfigurationService configurationService;

    /**
     * Catalog entity converter
     */
    @Autowired
    @Qualifier("catalogEntityConverter")
    protected CatalogEntityConverter catalogEntityConverter;

    /**
     * Catalog facade
     */
    @Autowired
    @Qualifier("catalogFacade")
    protected CatalogFacade catalogFacade;

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
        if (catalogFacade.getDefaultCatalog(httpServletRequest) == null) {
            String defaultCatalogCode = configurationService.getParameter(MercuriusConfigurationParameters.WEB_PARAMETERS.DEFAULT_CATALOG_CODE);
            /** Load default catalog */
            if (defaultCatalogCode != null) {
                CatalogEntity catalogEntity = uniqueCodeEntityService.getEntityByCode(defaultCatalogCode, CatalogEntity.class);
                if (catalogEntity != null) {
                    catalogFacade.setDefaultCatalog(httpServletRequest, catalogEntityConverter.convert(catalogEntity));
                } else {
                    throw new CatalogAbsenceException(defaultCatalogCode);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
