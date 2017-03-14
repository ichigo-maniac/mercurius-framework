package org.mercuriusframework.converters.impl;

import org.mercuriusframework.entities.RoleEntity;
import org.mercuriusframework.fillers.impl.RoleEntityFiller;
import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.RoleEntityDto;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Role entity converter
 */
@Service("roleEntityConverter")
public class RoleEntityConverter implements Converter<RoleEntity, RoleEntityDto>  {

    /**
     * Role entity filler
     */
    @Autowired
    @Qualifier("roleEntityFiller")
    protected RoleEntityFiller roleEntityFiller;

    /**
     * Convert a source object to a result object
     * @param roleEntity Source object
     * @param options    Load options
     * @return Result object
     */
    @Override
    public RoleEntityDto convert(RoleEntity roleEntity, LoadOptions... options) {
        RoleEntityDto result = new RoleEntityDto();
        roleEntityFiller.fillIn(roleEntity, result, options);
        return result;
    }
}
