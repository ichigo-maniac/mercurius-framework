package org.mercuriusframework.services.impl;

import org.mercuriusframework.services.CatalogService;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Catalog service
 */
@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {
    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;
}
