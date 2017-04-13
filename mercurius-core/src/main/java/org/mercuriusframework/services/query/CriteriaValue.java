package org.mercuriusframework.services.query;

import org.mercuriusframework.enums.CriteriaValueType;

/**
 * Criteria value
 */
public class CriteriaValue {

    /**
     * Type
     */
    private CriteriaValueType type;

    /**
     * Value
     */
    private Object value;

    /**
     * Constructor
     * @param type Type
     * @param value Value
     */
    public CriteriaValue(CriteriaValueType type, Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Get type
     * @return Type
     */
    public CriteriaValueType getType() {
        return type;
    }

    /**
     * Get value
     * @return Value
     */
    public Object getValue() {
        return value;
    }

}
