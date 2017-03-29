package org.mercuriusframework.entities;

import org.mercuriusframework.entities.attributes.FeatureEnumClass;
import org.mercuriusframework.entities.converters.FeatureEnumClassConverter;
import org.mercuriusframework.enums.FeatureType;

import javax.persistence.*;
import java.util.List;

/**
 * Feature entity class
 */
@Entity(name = FeatureEntity.ENTITY_NAME)
@Table(name = "FEATURE")
public class FeatureEntity extends CatalogUniqueCodeEntity {

    private static final long serialVersionUID = -8068212712665197705L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "Feature";

    /**
     * Enum class
     */
    @Convert(converter = FeatureEnumClassConverter.class)
    @Basic(optional = true)
    private FeatureEnumClass enumClass;
    public static final String ENUM_CLASS = "enumClass";

    /**
     * Feature type
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private FeatureType featureType;
    public static final String FEATURE_TYPE = "featureType";

    /**
     * Feature values
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = FeatureValueEntity.FEATURE, cascade = CascadeType.ALL)
    private List<FeatureValueEntity> featureValues;
    public static final String FEATURE_VALUES = "featureValues";

    /**
     * Get enum class
     * @return Enum class
     */
    public FeatureEnumClass getEnumClass() {
        return enumClass;
    }

    /**
     * Set enum class
     * @param enumClass Enum class
     */
    public void setEnumClass(FeatureEnumClass enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * Get feature type
     * @return Feature type
     */
    public FeatureType getFeatureType() {
        return featureType;
    }

    /**
     * Set feature type
     * @param featureType Feature type
     */
    public void setFeatureType(FeatureType featureType) {
        this.featureType = featureType;
    }

    /**
     * Get feature values
     * @return Feature values
     */
    public List<FeatureValueEntity> getFeatureValues() {
        return featureValues;
    }

    /**
     * Set feature values
     * @param featureValues Feature values
     */
    public void setFeatureValues(List<FeatureValueEntity> featureValues) {
        this.featureValues = featureValues;
    }
}
