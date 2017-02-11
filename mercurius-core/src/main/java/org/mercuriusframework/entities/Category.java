package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Category entity class
 */
@Entity
@Table(name = "CATEGORY")
public class Category extends CatalogUniqueCodeEntity {
    /**
     * Main super category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_SUPERCATEGORY_UUID", referencedColumnName = "uuid", nullable = true)
    private Category mainSuperCategory;
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
    /**
     * Sub categories
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORIES_SUBCATEGORIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "SUBCATEGORY_UUID",
                            nullable = false, updatable = false)})
    private Set<Category> subCategories;

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
