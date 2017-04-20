package org.mercuriusframework.dto;

/**
 * Dictionary item entity data transfer object
 */
public class DictionaryItemEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = -7572766126017479791L;

    /**
     * Dictionary type
     */
    protected DictionaryTypeEntityDto dictionaryType;

    /**
     * Get dictionary type
     * @return Dictionary type
     */
    public DictionaryTypeEntityDto getDictionaryType() {
        return dictionaryType;
    }

    /**
     * Set dictionary type
     * @param dictionaryType Dictionary type
     */
    public void setDictionaryType(DictionaryTypeEntityDto dictionaryType) {
        this.dictionaryType = dictionaryType;
    }
}
