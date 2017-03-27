package org.mercuriusframework.entities.attributes;

/**
 * Feature enum class
 */
public class FeatureEnumClass {

    /**
     * Feature enum class
     */
    private Class classValue;

    /**
     * Constructor
     * @param classValue Class value
     */
    public FeatureEnumClass(Class classValue) {
        this.classValue = classValue;
    }

    /**
     * Get class value
     * @return Class value
     */
    public Class getClassValue() {
        return classValue;
    }
}
