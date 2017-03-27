package org.mercuriusframework.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Load options interface
 */
public interface LoadOptions {

    /**
     * Get enum value
     * @return Enum value
     */
    String getValue();

    /**
     * Get enum value
     * @return Array of values
     */
    LoadOptions[] getValues();

    /**
     * Get enum value from string value
     * @param value String value
     * @return Enum value or null
     */
    default LoadOptions valueFromString(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (LoadOptions loadOption : getValues()) {
            if (loadOption.getValue().equalsIgnoreCase(value.toLowerCase())) {
                return loadOption;
            }
        }
        return null;
    }
}
