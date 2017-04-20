package org.mercuriusframework.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Dictionary type entity class
 */
@Entity(name = DictionaryTypeEntity.ENTITY_NAME)
@Table(name = "DICTIONARY_TYPES")
public class DictionaryTypeEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 7434525061916639240L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "DictionaryType";

    /**
     * Dictionary items
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = DictionaryItemEntity.DICTIONARY_TYPE, cascade = CascadeType.ALL)
    private List<DictionaryItemEntity> dictionaryItems;
    public static final String DICTIONARY_ITEMS = "dictionaryItems";

    /**
     * Features
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = FeatureEntity.DICTIONARY_TYPE, cascade = CascadeType.ALL)
    private List<FeatureEntity> features;
    public static final String FEATURES = "features";


    /**
     * Get dictionary items
     * @return Dictionary items
     */
    public List<DictionaryItemEntity> getDictionaryItems() {
        return dictionaryItems;
    }

    /**
     * Set dictionary items
     * @param dictionaryItems Dictionary items
     */
    public void setDictionaryItems(List<DictionaryItemEntity> dictionaryItems) {
        this.dictionaryItems = dictionaryItems;
    }

    /**
     * Get features
     * @return Features
     */
    public List<FeatureEntity> getFeatures() {
        return features;
    }

    /**
     * Set features
     * @param features Features
     */
    public void setFeatures(List<FeatureEntity> features) {
        this.features = features;
    }
}
