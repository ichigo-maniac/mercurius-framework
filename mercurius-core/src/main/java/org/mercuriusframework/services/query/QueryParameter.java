package org.mercuriusframework.services.query;

/**
 * Query parameter class
 */
public class QueryParameter {
    /**
     *  Query parameter key
     */
    private String key;
    /**
     * Query parameter value
     */
    private Object value;

    /**
     * Constructor
     * @param key Query parameter key
     * @param value Query parameter value
     */
    public QueryParameter(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get Query parameter key
     * @return Query parameter key
     */
    public String getKey() {
        return key;
    }

    /**
     * Get Query parameter value
     * @return Query parameter value
     */
    public Object getValue() {
        return value;
    }
}
