package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.dto.UserEntityDto;
import org.mercuriusframework.entities.AbstractUserEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.springframework.stereotype.Service;


/**
 * User entity filler
 */
@Service("userEntityFiller")
public class UserEntityFiller<SOURCE extends AbstractUserEntity, RESULT extends UserEntityDto> extends UniqueCodeEntityFiller<SOURCE, RESULT> {

    /**
     * Fill a result object from a source object
     * @param source  Source object
     * @param result  Result object
     * @param options Load options
     */
    @Override
    public void fillIn(SOURCE source, RESULT result, LoadOptions... options) {
        super.fillIn(source, result, options);
        result.setEmail(source.getEmail());
        result.setPassword(source.getPassword());
        result.setAuthenticationType(source.getAuthenticationType());
        result.setPhoneNumber(source.getPhoneNumber());
    }
}
