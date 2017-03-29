package org.mercuriusframework.entities.converters;

import org.mercuriusframework.entities.attributes.FeatureEnumClass;
import org.mercuriusframework.enums.FeatureValueEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/**
 * Feature enum class converter
 */
@Convert
public class FeatureEnumClassConverter implements AttributeConverter<FeatureEnumClass, String> {

    /**
     * Convert to database column
     * @param featureEnumClass Feature enum class
     * @return Column data
     */
    @Override
    public String convertToDatabaseColumn(FeatureEnumClass featureEnumClass) {
        if (featureEnumClass == null) {
            return null;
        } else {
            return featureEnumClass.getClassValue().toGenericString();
        }
    }

    /**
     * Convert to entity attribute
     * @param rawValue Raw string value
     * @return Parsed feature enum class
     *
     */
    @Override
    public FeatureEnumClass convertToEntityAttribute(String rawValue) {
        if (rawValue == null) {
            return null;
        }
        try {
            Class result = Class.forName(rawValue);
            return result.isAssignableFrom(FeatureValueEnum.class) ? new FeatureEnumClass(result) : null;
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
