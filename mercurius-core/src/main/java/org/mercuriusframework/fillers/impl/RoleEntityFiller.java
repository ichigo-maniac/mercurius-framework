package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.RoleEntityDto;
import org.mercuriusframework.entities.RoleEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;

/**
 * Role entity filler
 */
@Service("roleEntityFiller")
public class RoleEntityFiller extends UniqueCodeEntityFiller<RoleEntity, RoleEntityDto> {
    /**
     * Fill a result object from a source object
     * @param roleEntity    Source object
     * @param roleEntityDto Result object
     * @param options       Load options
     */
    @Override
    public void fillIn(RoleEntity roleEntity, RoleEntityDto roleEntityDto, LoadOptions... options) {
        super.fillIn(roleEntity, roleEntityDto, options);
    }
}
