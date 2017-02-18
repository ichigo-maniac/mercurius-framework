package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Product entity class
 */
@Entity(name = Product.ENTITY_NAME)
@Table(name = "PRODUCT")
public class Product extends CatalogUniqueCodeEntity {
    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Product";

    /**
     * Main unit
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_UNIT_UUID", referencedColumnName = "uuid", nullable = false)
    private Unit mainUnit;
    public static final String MAIN_UNIT = "mainUnit";

    /**
     * All available units
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_UNITS_LINK",
            joinColumns = {
                    @JoinColumn(name = "PRODUCT_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "UNIT_UUID",
                            nullable = false, updatable = false)})
    private Set<Unit> units;
    public static final String UNITS = "units";

    /**
     * Main category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_CATEGORY_UUID", referencedColumnName = "UUID", nullable = false)
    private CategoryEntity mainCategory;
    public static final String MAIN_CATEGORY = "mainCategory";

    /**
     * All product categories
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_CATEGORIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "PRODUCT_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)})
    private Set<CategoryEntity> categories;
    public static final String CATEGORIES = "categories";

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
    public CategoryEntity getMainCategory() {
        return mainCategory;
    }

    /**
     * Set main category
     * @param mainCategory Main category
     */
    public void setMainCategory(CategoryEntity mainCategory) {
        this.mainCategory = mainCategory;
    }

    /**
     * Get all product categories
     * @return Set of categories
     */
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    /**
     * Set all product categories
     * @param categories Set of categories
     */
    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
