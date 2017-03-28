package org.mercuriusframework.entities;

import org.mercuriusframework.entities.attributes.FeatureValue;
import org.mercuriusframework.entities.converters.FeatureValueConverter;
import javax.persistence.*;

/**
 * Feature value entity class
 */
@Entity(name = FeatureValueEntity.ENTITY_NAME)
@Table(name = "FEATURE_VALUE")
public class FeatureValueEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = -1680066194915805527L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "FeatureValue";

    /**
     * Feature
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "FEATURE_UUID", referencedColumnName = "uuid", nullable = false)
    private FeatureEntity feature;
    public static final String FEATURE = "feature";

    /**
     * Product
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_UUID", referencedColumnName = "uuid", nullable = false)
    private ProductEntity product;
    public static final String PRODUCT = "product";

    /**
     * Group name
     */
    @Basic(optional = true)
    private String groupName;
    public static final String GROUP_NAME = "groupName";

    /**
     * Value
     */
    @Convert(converter = FeatureValueConverter.class)
    @Column(name = "featureValue")
    @Basic(optional = false)
    private FeatureValue value;
    public static final String VALUE = "value";

    /**
     * Get feature
     * @return Feature
     */
    public FeatureEntity getFeature() {
        return feature;
    }

    /**
     * Set feature
     * @param feature Feature
     */
    public void setFeature(FeatureEntity feature) {
        this.feature = feature;
    }

    /**
     * Get product
     * @return Product
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Set product
     * @param product Product
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    /**
     * Get group name
     * @return Group name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set group name
     * @param groupName Group name
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Get value
     * @return Value
     */
    public FeatureValue getValue() {
        return value;
    }

    /**
     * Set value
     * @param value Value
     */
    public void setValue(FeatureValue value) {
        this.value = value;
    }
}
