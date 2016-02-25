package org.mercuriusframework.services.impl;

import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product service
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    /**
     * Entity service
     */
    @Autowired
    private EntityService entityService;
}
