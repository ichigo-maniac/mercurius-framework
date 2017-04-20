package org.mercuriusframework.entities;

import javax.persistence.*;

/**
 * Dictionary item entity class
 */
@Entity(name = DictionaryItemEntity.ENTITY_NAME)
@Table(name = "DICTIONARY_ITEMS")
public class DictionaryItemEntity extends UniqueCodeEntity {

    private static final long serialVersionUID = 7208019495905790658L;

    /**
     * Entity name
     */
    public static final String ENTITY_NAME = "DictionaryItem";

    /**
     * Dictionary type
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DICTIONARY_TYPE_UUID", referencedColumnName = "uuid", nullable = false)
    private DictionaryTypeEntity dictionaryType;
    public static final String DICTIONARY_TYPE = "dictionaryType";

    /**
     * Get dictionary type
     * @return Dictionary type
     */
    public DictionaryTypeEntity getDictionaryType() {
        return dictionaryType;
    }

    /**
     * Set dictionary type
     * @param dictionaryType Dictionary type
     */
    public void setDictionaryType(DictionaryTypeEntity dictionaryType) {
        this.dictionaryType = dictionaryType;
    }
}
