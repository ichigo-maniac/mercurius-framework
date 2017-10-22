package org.mercuriusframework.enums;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;

/**
 * Solr sort direction enum
 */
public enum SolrSortDirectionType {

    /**
     * Values
     */
    ASC("ASC"),
    DESC("DESC");

    /**
     * Enum value
     */
    private String value;

    /**
     * Constructor
     * @param value Enum value
     */
    SolrSortDirectionType(String value) {
        this.value = value;
    }

    /**
     * Get value
     * @return Value
     */
    public String getValue() {
        return value;
    }

    /**
     * Get value from string
     * @param value String value
     * @return Enum value
     */
    public static SolrSortDirectionType valueFromString(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        for (SolrSortDirectionType featureValue : SolrSortDirectionType.values()) {
            if (featureValue.getValue().equalsIgnoreCase(value.toLowerCase())) {
                return featureValue;
            }
        }
        return null;
    }

    /**
     * Transform direction
     * @param directionType Direction type
     * @return Sort direction
     */
    public static Sort.Direction transformDirection(SolrSortDirectionType directionType) {
        if (directionType == null) {
            return Sort.Direction.ASC;
        }
        if (directionType == ASC) {
            return Sort.Direction.ASC;
        }
        if (directionType == DESC) {
            return Sort.Direction.DESC;
        } else {
            return Sort.Direction.ASC;
        }
    }
}
