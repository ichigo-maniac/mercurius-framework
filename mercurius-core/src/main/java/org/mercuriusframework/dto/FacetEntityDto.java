package org.mercuriusframework.dto;

import org.mercuriusframework.enums.FacetType;
import java.util.List;

/**
 * Facet entity data transfer object
 */
public class FacetEntityDto extends UniqueCodeEntityDto {

    private static final long serialVersionUID = 8840337980679791485L;

    /**
     * Facet type
     */
    private FacetType facetType;

    /**
     * Available values
     */
    private List<DictionaryItemEntityDto> availableValues;

    /**
     * Get facet type
     * @return Facet type
     */
    public FacetType getFacetType() {
        return facetType;
    }

    /**
     * Set facet type
     * @param facetType Facet type
     */
    public void setFacetType(FacetType facetType) {
        this.facetType = facetType;
    }

    /**
     * Get available values
     * @return Available values
     */
    public List<DictionaryItemEntityDto> getAvailableValues() {
        return availableValues;
    }

    /**
     * Set available values
     * @param availableValues Available values
     */
    public void setAvailableValues(List<DictionaryItemEntityDto> availableValues) {
        this.availableValues = availableValues;
    }
}
