package org.mercuriusframework.security;

import org.mercuriusframework.entities.AbstractUserEntity;

/**
 * Salt service interface
 */
public interface SaltService {

    /**
     * Get salt object
     * @param userEntity User entity
     * @return Salt object
     */
    String getSalt(AbstractUserEntity userEntity);
}
