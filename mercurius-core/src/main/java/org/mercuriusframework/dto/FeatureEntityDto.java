package org.mercuriusframework.dto;

import org.mercuriusframework.enums.FeatureType;

/**
 * Feature entity data transfer object
 */
public class FeatureEntityDto extends CatalogUniqueCodeEntityDto {

    private static final long serialVersionUID = 6862681302524349596L;

    /**
     * Feature type
     */
    private FeatureType featureType;

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
}
