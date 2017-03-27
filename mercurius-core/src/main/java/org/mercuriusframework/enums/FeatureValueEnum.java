package org.mercuriusframework.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Feature value eum interface
 */
public interface FeatureValueEnum {

    /**
     * Value from string method name constant
     */
    String VALUE_FROM_STRING_METHOD = "valueFromString";

    /**
     * Get full class name
     * @return Full class name
     */
    String getFullClassName();

    /**
     * Get enum value
     * @return Enum value
     */
    String getValue();

    /**
     * Get enum values
     * @return Array of enum values
     */
    FeatureValueEnum[] getValues();
    /**
     * Get enum value from string value
     * @param value String value
     * @return Enum value or null
     */
    default FeatureValueEnum valueFromString(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (FeatureValueEnum featureValue : getValues()) {
            if (featureValue.getValue().equalsIgnoreCase(value.toLowerCase())) {
                return featureValue;
            }
        }
        return null;
    }
}
