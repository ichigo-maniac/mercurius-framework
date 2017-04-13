package org.mercuriusframework.services.query;

/**
 * Criteria parameter
 */
public class CriteriaParameter {

    /**
     * Entity property
     */
    private String property;

    /**
     * Values
     */
    private CriteriaValue[] values;

    /**
     * Constructor
     * @param property Entity property
     * @param value Single value
     */
    public CriteriaParameter(String property, CriteriaValue value) {
        this.property = property;
        this.values = new CriteriaValue[1];
        this.values[0] = value;
    }

    /**
     * Constructor
     * @param property Entity property
     * @param values Values
     */
    public CriteriaParameter(String property, CriteriaValue... values) {
        this.property = property;
        this.values = values;
    }

    /**
     * Get entity property
     * @return Entity property
     */
    public String getProperty() {
        return property;
    }

    /**
     * Get values
     * @return Values
     */
    public CriteriaValue[] getValues() {
        return values;
    }
}
