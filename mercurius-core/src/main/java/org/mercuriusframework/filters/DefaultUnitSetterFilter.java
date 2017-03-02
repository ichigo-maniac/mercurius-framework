package org.mercuriusframework.filters;

import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.impl.CatalogEntityConverter;
import org.mercuriusframework.converters.impl.UnitEntityConverter;
import org.mercuriusframework.dto.CatalogEntityDto;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.entities.UnitEntity;
import org.mercuriusframework.exceptions.UnitAbsenceException;
import org.mercuriusframework.facades.CatalogFacade;
import org.mercuriusframework.facades.UnitFacade;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.mercuriusframework.services.ConfigurationService;
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
 * Default unit setter filter
 */
@Service("defaultUnitSetterFilter")
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class DefaultUnitSetterFilter extends OncePerRequestFilter {

    /**
     * Catalog unique code entity service
     */
    @Autowired
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Catalog entity converter
     */
    @Autowired
    @Qualifier("catalogEntityConverter")
    protected CatalogEntityConverter catalogEntityConverter;

    /**
     * Configuration service
     */
    @Autowired
    @Qualifier("configurationService")
    private ConfigurationService configurationService;

    /**
     * Unit entity converter
     */
    @Autowired
    @Qualifier("unitEntityConverter")
    private UnitEntityConverter unitEntityConverter;

    /**
     * Catalog facade
     */
    @Autowired
    @Qualifier("catalogFacade")
    private CatalogFacade catalogFacade;

    /**
     * Unit facade
     */
    @Autowired
    @Qualifier("unitFacade")
    private UnitFacade unitFacade;

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
        if (unitFacade.getDefaultUnit(httpServletRequest) == null) {
            CatalogEntityDto defaultCatalog = catalogFacade.getDefaultCatalog(httpServletRequest);
            if (defaultCatalog != null) {
                String defaultUnitCode = configurationService.getParameter(MercuriusConfigurationParameters.WEB_PARAMETERS.DEFAULT_UNIT_CODE);
                /** Load default unit */
                if (defaultUnitCode != null) {
                    UnitEntity unitEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(defaultUnitCode, defaultCatalog.getCode(), UnitEntity.class);
                    if (unitEntity != null) {
                        unitFacade.setDefaultUnit(httpServletRequest, unitEntityConverter.convert(unitEntity));
                    } else {
                        throw new UnitAbsenceException(defaultUnitCode);
                    }
                }
            } else {
                String catalogCodeForUnit = configurationService.getParameter(MercuriusConfigurationParameters.WEB_PARAMETERS.CATALOG_CODE_FOR_DEFAULT_UNIT_CODE);
                String defaultUnitCode = configurationService.getParameter(MercuriusConfigurationParameters.WEB_PARAMETERS.DEFAULT_UNIT_CODE);
                if (catalogCodeForUnit != null || defaultUnitCode != null) {
                    /** Load default unit */
                    if (catalogCodeForUnit != null && defaultUnitCode != null) {
                        UnitEntity unitEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(defaultUnitCode, catalogCodeForUnit, UnitEntity.class);
                        if (unitEntity != null) {
                            unitFacade.setDefaultUnit(httpServletRequest, unitEntityConverter.convert(unitEntity));
                        } else {
                            throw new UnitAbsenceException(defaultUnitCode, catalogCodeForUnit);
                        }
                    } else {
                        throw new RuntimeException("It's impossible to set a default unit without preset default catalog or preset parameter "
                                + MercuriusConfigurationParameters.WEB_PARAMETERS.CATALOG_CODE_FOR_DEFAULT_UNIT_CODE);
                    }
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
