package org.mercuriusframework.entities.attributes;

import org.mercuriusframework.entities.DictionaryItemEntity;
import org.mercuriusframework.enums.FeatureType;

/**
 * Feature value
 */
public class FeatureValue {

    /**
     * String value
     */
    private String stringValue;

    /**
     * Numeric value
     */
    private Integer numericValue;

    /**
     * Float numeric value
     */
    private Double floatNumericValue;

    /**
     * Boolean value
     */
    private Boolean booleanValue;

    /**
     * Dictionary item value
     */
    private DictionaryItemEntity dictionaryItemValue;


    /**
     * Constructor
     * @param stringValue String value
     */
    public FeatureValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * Constructor
     * @param numericValue Numeric value
     */
    public FeatureValue(Integer numericValue) {
        this.numericValue = numericValue;
    }

    /**
     * Constructor
     * @param floatNumericValue Float numeric value
     */
    public FeatureValue(Double floatNumericValue) {
        this.floatNumericValue = floatNumericValue;
    }

    /**
     * Constructor
     * @param booleanValue Boolean value
     */
    public FeatureValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    /**
     * Constructor
     * @param dictionaryItemValue Dictionary item value
     */
    public FeatureValue(DictionaryItemEntity dictionaryItemValue) {
        this.dictionaryItemValue = dictionaryItemValue;
    }

    /**
     * Get feature value
     * @return Value
     */
    public Object getValue() {
        if (stringValue != null) {
            return stringValue;
        }
        if (numericValue != null) {
            return numericValue;
        }
        if (floatNumericValue != null) {
            return floatNumericValue;
        }
        if (booleanValue != null) {
            return booleanValue;
        }
        if (dictionaryItemValue != null) {
            return dictionaryItemValue;
        }
        return null;
    }

    /**
     * Get feature value type
     * @return Feature value type
     */
    public FeatureType getType() {
        if (stringValue != null) {
            return FeatureType.STRING_TYPE;
        }
        if (numericValue != null) {
            return FeatureType.NUMERIC_TYPE;
        }
        if (floatNumericValue != null) {
            return FeatureType.FLOAT_NUMERIC_TYPE;
        }
        if (booleanValue != null) {
            return FeatureType.BOOLEAN_TYPE;
        }
        if (dictionaryItemValue != null) {
            return FeatureType.DICTIONARY_TYPE;
        }
        return null;
    }

    /**
     * To string
     * @return String representation
     */
    @Override
    public String toString() {
        Object value = getValue();
        if (value instanceof DictionaryItemEntity) {
            return ((DictionaryItemEntity) value).getName();
        }
        return getValue() != null ? getValue().toString() : "";
    }
}
