package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Product entity class
 */
@Entity(name = ProductEntity.ENTITY_NAME)
@Table(name = "PRODUCT")
public class ProductEntity extends CatalogUniqueCodeEntity {
    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Product";

    /**
     * Short name
     */
    @Basic(optional = true)
    private String shortName;
    public static final String SHORT_NAME = "shortName";

    /**
     * Description
     */
    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "DESCRIPTION_UUID")
    private BigStringEntity description;
    public static final String DESCRIPTION = "description";

    /**
     * Main unit
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_UNIT_UUID", referencedColumnName = "uuid", nullable = false)
    private UnitEntity mainUnit;
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
    private Set<UnitEntity> units;
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
     * Get short name
     * @return Short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Set short name
     * @param shortName Short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Get description
     * @return Description
     */
    public BigStringEntity getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description Description
     */
    public void setDescription(BigStringEntity description) {
        this.description = description;
    }

    /**
     * Get main unit
     * @return Main unit
     */
    public UnitEntity getMainUnit() {
        return mainUnit;
    }

    /**
     * Set main unit
     * @param mainUnit Main unit

     */
    public void setMainUnit(UnitEntity mainUnit) {
        this.mainUnit = mainUnit;
    }

    /**
     * Get lll available units
     * @return Set of units
     */
    public Set<UnitEntity> getUnits() {
        return units;
    }

    /**
     * Set all available units
     * @param units Set of units
     */
    public void setUnits(Set<UnitEntity> units) {
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
