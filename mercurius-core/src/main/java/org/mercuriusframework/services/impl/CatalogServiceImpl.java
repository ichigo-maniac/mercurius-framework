package org.mercuriusframework.services.impl;

import org.mercuriusframework.services.CatalogService;
import org.mercuriusframework.services.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EntityService entityService;
}
