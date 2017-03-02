package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Unit entity class
 */
@Entity(name = UnitEntity.ENTITY_NAME)
@Table(name = "UNIT")
public class UnitEntity extends CatalogUniqueCodeEntity {
    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Unit";

    /**
     *  Products
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_UNITS_LINK",
            joinColumns = {
                    @JoinColumn(name = "UNIT_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_UUID",
                            nullable = false, updatable = false)})
    private Set<ProductEntity> products;
    public static final String PRODUCTS = "products";

    /**
     * Get products
     * @return Products
     */
    public Set<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Set products
     * @param products Products
     */
    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
