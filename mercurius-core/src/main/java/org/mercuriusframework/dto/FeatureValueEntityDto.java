package org.mercuriusframework.dto;

/**
 * Feature value entity data transfer object
 */
public class FeatureValueEntityDto extends CatalogUniqueCodeEntityDto {

    /**
     * Feature
     */
    private FeatureEntityDto feature;

    /**
     * Value
     */
    private Object value;

    /**
     * Group name
     */
    private String groupName;

    /**
     * Get feature
     * @return Feature
     */
    public FeatureEntityDto getFeature() {
        return feature;
    }

    /**
     * Set feature
     * @param feature Feature
     */
    public void setFeature(FeatureEntityDto feature) {
        this.feature = feature;
    }

    /**
     * Get value
     * @return Value
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set value
     * @param value Value
     */
    public void setValue(Object value) {
        this.value = value;
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
}
