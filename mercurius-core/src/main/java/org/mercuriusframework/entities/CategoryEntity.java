package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Category entity class
 */
@Entity(name = CategoryEntity.ENTITY_NAME)
@Table(name = "CATEGORIES")
public class CategoryEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = -1108095657379342612L;

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
    @JoinTable(name = "CATEGORIES_SUPERCATEGORIES_LINKS",
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
    @JoinTable(name = "CATEGORIES_SUPERCATEGORIES_LINKS",
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
    @JoinTable(name = "PRODUCTS_CATEGORIES_LINKS",
            joinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "PRODUCT_UUID",
                            nullable = false, updatable = false)})
    private Set<ProductEntity> products;
    public static final String PRODUCTS = "products";

    /**
     * Facets
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FACETS_CATEGORIES_LINKS",
            joinColumns = {
                    @JoinColumn(name = "CATEGORY_UUID",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "FACET_UUID",
                            nullable = false, updatable = false)})
    private List<FacetEntity> facets;
    public static final String FACETS = "facets";

    /**
     * Priority
     */
    @Basic(optional = true)
    private Integer priority;
    public static final String PRIORITY = "priority";

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

    /**
     * Get facets
     * @return Facets
     */
    public List<FacetEntity> getFacets() {
        return facets;
    }

    /**
     * Set facets
     * @param facets Facets
     */
    public void setFacets(List<FacetEntity> facets) {
        this.facets = facets;
    }

    /**
     * Get priority
     * @return Priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Set priority
     * @param priority Priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
