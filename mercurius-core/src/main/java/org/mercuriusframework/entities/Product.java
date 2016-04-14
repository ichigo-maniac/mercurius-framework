package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Product entity class
 */
@Entity
@Table(name = "PRODUCT")
@SequenceGenerator(name = "entity_id_gen", sequenceName = "PRODUCT_SEQ",
        allocationSize = 1, initialValue = 1)
public class Product extends CatalogUniqueCodeEntity {
    /**
     * Main unit
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_UNIT_ID", referencedColumnName = "id", nullable = false)
    private Unit mainUnit;
    /**
     * All available units
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_UNITS_LINK",
            joinColumns = {
                    @JoinColumn(name = "PRODUCT_ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "UNIT_ID",
                            nullable = false, updatable = false)})
    private Set<Unit> units;
    /**
     * Main category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_CATEGORY_ID", referencedColumnName = "id", nullable = false)
    private Category mainCategory;
    /**
     * All product categories
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_CATEGORIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "PRODUCT_ID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CATEGORY_ID",
                            nullable = false, updatable = false)})
    private Set<Category> categories;

    /**
     * Get main unit
     * @return Main unit
     */
    public Unit getMainUnit() {
        return mainUnit;
    }

    /**
     * Set main unit
     * @param mainUnit Main unit

     */
    public void setMainUnit(Unit mainUnit) {
        this.mainUnit = mainUnit;
    }

    /**
     * Get lll available units
     * @return Set of units
     */
    public Set<Unit> getUnits() {
        return units;
    }

    /**
     * Set all available units
     * @param units Set of units
     */
    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    /**
     * Get main category
     * @return Main category
     */
    public Category getMainCategory() {
        return mainCategory;
    }

    /**
     * Set main category
     * @param mainCategory Main category
     */
    public void setMainCategory(Category mainCategory) {
        this.mainCategory = mainCategory;
    }

    /**
     * Get all product categories
     * @return Set of categories
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * Set all product categories
     * @param categories Set of categories
     */
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
