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
     * Get enum value from string value
     * @param value String value
     * @param enumValues Available enums
     * @return Enum value or null
     */
    default LoadOptions valueFromString(String value, LoadOptions enumValues[]) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (LoadOptions loadOption : enumValues) {
            if (loadOption.getValue().toLowerCase().equals(value.toLowerCase())) {
                return loadOption;
            }
        }
        return null;
    }
}
