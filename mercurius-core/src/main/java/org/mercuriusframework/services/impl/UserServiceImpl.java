package org.mercuriusframework.services.impl;

import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User service
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;
}
