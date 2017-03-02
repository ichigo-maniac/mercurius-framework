package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.dto.UnitEntityDto;
import org.mercuriusframework.facades.UnitFacade;
import org.mercuriusframework.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Unit facade
 */
@Service("unitFacade")
public class UnitFacadeImpl implements UnitFacade {
    /**
     * Session service
     */
    @Autowired
    @Qualifier("sessionService")
    protected SessionService sessionService;

    /**
     * Get default unit
     * @return Unit data transfer object
     */
    @Override
    public UnitEntityDto getDefaultUnit() {
        return (UnitEntityDto) sessionService.getSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_UNIT);
    }

    /**
     * Get default unit
     * @param request Http-request
     * @return Unit data transfer object
     */
    @Override
    public UnitEntityDto getDefaultUnit(HttpServletRequest request) {
        return (UnitEntityDto) sessionService.getSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_UNIT);
    }

    /**
     * Set default unit
     * @param unit Unit data transfer object
     */
    @Override
    public void setDefaultUnit(UnitEntityDto unit) {
        sessionService.setSessionAttribute(MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_UNIT, unit);
    }

    /**
     * Set default unit
     * @param request Http-request
     * @param unit    Unit data transfer object
     */
    @Override
    public void setDefaultUnit(HttpServletRequest request, UnitEntityDto unit) {
        sessionService.setSessionAttribute(request, MercuriusConstants.SESSION_ATTRIBUTES.DEFAULT_UNIT, unit);
    }
}
