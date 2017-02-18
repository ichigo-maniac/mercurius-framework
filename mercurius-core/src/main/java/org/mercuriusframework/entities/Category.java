package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Category entity class
 */
@Entity(name = Category.ENTITY_NAME)
@Table(name = "CATEGORY")
public class Category extends CatalogUniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Category";

    /**
     * Main super category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_SUPERCATEGORY_UUID", referencedColumnName = "uuid", nullable = true)
    private Category mainSuperCategory;
    public static final String MAIN_SUPER_CATEGORY = "mainSuperCategory";

    /**
     * Super categories
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORIES_SUPERCATEGORIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "SUPERCATEGORY_UUID",
                            nullable = false, updatable = false)})
    private Set<Category> superCategories;
    public static final String SUPER_CATEGORIES = "superCategories";

    /**
     * Sub categories
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORIES_SUPERCATEGORIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "SUPERCATEGORY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)})
    private Set<Category> subCategories;
    public static final String SUB_CATEGORIES = "subCategories";

    /**
     * Get sub categories
     *
     * @return Set of categories
     */
    public Set<Category> getSubCategories() {
        return subCategories;
    }

    /**
     * Set sub categories
     *
     * @param subCategories Set of categories
     */
    public void setSubCategories(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }

    /**
     * Get super categories
     *
     * @return Set of categories
     */
    public Set<Category> getSuperCategories() {
        return superCategories;
    }

    /**
     * Set super categories
     *
     * @param superCategories Set of categories
     */
    public void setSuperCategories(Set<Category> superCategories) {
        this.superCategories = superCategories;
    }

    /**
     * Get main super category
     *
     * @return Category
     */
    public Category getMainSuperCategory() {
        return mainSuperCategory;
    }

    /**
     * Set main super category
     *
     * @param mainSuperCategory Category
     */
    public void setMainSuperCategory(Category mainSuperCategory) {
        this.mainSuperCategory = mainSuperCategory;
    }
}
