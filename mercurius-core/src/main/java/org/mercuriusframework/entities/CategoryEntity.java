package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Category entity class
 */
@Entity(name = CategoryEntity.ENTITY_NAME)
@Table(name = "CATEGORY")
public class CategoryEntity extends CatalogUniqueCodeEntity {

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Category";

    /**
     * Description
     */
    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "DESCRIPTION_UUID")
    private BigStringEntity description;
    public static final String DESCRIPTION = "description";

    /**
     * Main super category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAIN_SUPERCATEGORY_UUID", referencedColumnName = "uuid", nullable = true)
    private CategoryEntity mainSuperCategory;
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
    private Set<CategoryEntity> superCategories;
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
    private Set<CategoryEntity> subCategories;
    public static final String SUB_CATEGORIES = "subCategories";

    /**
     * All products
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCTS_CATEGORIES_LINK",
            joinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_UUID",
                            nullable = false, updatable = false)})
    private Set<ProductEntity> products;
    public static final String PRODUCTS = "products";

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
     * Get sub categories
     * @return Set of categories
     */
    public Set<CategoryEntity> getSubCategories() {
        return subCategories;
    }

    /**
     * Set sub categories
     * @param subCategories Set of categories
     */
    public void setSubCategories(Set<CategoryEntity> subCategories) {
        this.subCategories = subCategories;
    }

    /**
     * Get super categories
     * @return Set of categories
     */
    public Set<CategoryEntity> getSuperCategories() {
        return superCategories;
    }

    /**
     * Set super categories
     * @param superCategories Set of categories
     */
    public void setSuperCategories(Set<CategoryEntity> superCategories) {
        this.superCategories = superCategories;
    }

    /**
     * Get main super category
     * @return Category
     */
    public CategoryEntity getMainSuperCategory() {
        return mainSuperCategory;
    }

    /**
     * Set main super category
     * @param mainSuperCategory Category
     */
    public void setMainSuperCategory(CategoryEntity mainSuperCategory) {
        this.mainSuperCategory = mainSuperCategory;
    }

    /**
     * Get all products
     * @return Set of products
     */
    public Set<ProductEntity> getProducts() {
        return products;
    }

    /**
     * Set products
     * @param products Set of products
     */
    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }
}
