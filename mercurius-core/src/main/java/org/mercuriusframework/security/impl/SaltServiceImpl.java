package org.mercuriusframework.security.impl;

import org.mercuriusframework.entities.AbstractUserEntity;
import org.mercuriusframework.security.SaltService;
import org.springframework.stereotype.Service;

/**
 * Salt service
 */
@Service("saltService")
public class SaltServiceImpl implements SaltService {

    /**
     * Instant salt path
     */
    private static final String INSTANT_SALT_PATH = "mercurius-framework-salt";

    /**
     * Get salt object
     * @param userEntity User entity
     * @return Salt object
     */
    @Override
    public String getSalt(AbstractUserEntity userEntity) {
        return userEntity != null ? INSTANT_SALT_PATH +  userEntity.getUuid() : null;
    }
}
