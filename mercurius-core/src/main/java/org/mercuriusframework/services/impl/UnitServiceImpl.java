package org.mercuriusframework.services.impl;

import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Unit service
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {
    /**
     * Entity service
     */
    @Autowired
    protected EntityService entityService;
}
