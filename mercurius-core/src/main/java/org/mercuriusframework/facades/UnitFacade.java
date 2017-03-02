package org.mercuriusframework.facades;

import org.mercuriusframework.dto.UnitEntityDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Unit facade interface
 */
public interface UnitFacade {
    /**
     * Get default unit
     * @return Unit data transfer object
     */
    UnitEntityDto getDefaultUnit();

    /**
     * Get default unit
     * @param request Http-request
     * @return Unit data transfer object
     */
    UnitEntityDto getDefaultUnit(HttpServletRequest request);

    /**
     * Set default unit
     * @param unit Unit data transfer object
     */
    void setDefaultUnit(UnitEntityDto unit);

    /**
     * Set default unit
     * @param request Http-request
     * @param unit Unit data transfer object
     */
    void setDefaultUnit(HttpServletRequest request, UnitEntityDto unit);
}
