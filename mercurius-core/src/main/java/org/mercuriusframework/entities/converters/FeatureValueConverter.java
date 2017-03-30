package org.mercuriusframework.entities.converters;

import org.mercuriusframework.entities.attributes.FeatureValue;
import org.mercuriusframework.enums.FeatureType;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
/**
 * Feature value converter
 */
@Convert
public class FeatureValueConverter implements AttributeConverter<FeatureValue, String> {
    /**
     * Splitter
     */
    private static final String SPLITTER = "::";

    /**
     * Convert to database column
     * @param featureValue Feature value
     * @return Column data
     */
    @Override
    public String convertToDatabaseColumn(FeatureValue featureValue) {
        FeatureType featureType = featureValue.getType();
        if (featureType == FeatureType.STRING_TYPE || featureType == FeatureType.NUMERIC_TYPE ||
                featureType == FeatureType.FLOAT_NUMERIC_TYPE || featureType == FeatureType.BOOLEAN_TYPE) {
            return featureType.getValue() + SPLITTER + featureValue.getValue().toString();
        }
        return null;
    }

    /**
     * Convert to entity attribute
     * @param rawValue Raw string value
     * @return Parsed feature value
     */
    @Override
    public FeatureValue convertToEntityAttribute(String rawValue) {
        String values[] = rawValue.split(SPLITTER);
        if (values.length != 2) {
            return null;
        }
        /** Parse values */
        FeatureType featureType = FeatureType.valueFromString(values[0]);
        if (featureType == null) {
            return null;
        }
        if (featureType == FeatureType.STRING_TYPE) {
            return new FeatureValue(values[1]);
        }
        if (featureType == FeatureType.NUMERIC_TYPE) {
            return new FeatureValue(Integer.valueOf(values[1]));
        }
        if (featureType == FeatureType.FLOAT_NUMERIC_TYPE) {
            return new FeatureValue(Double.valueOf(values[1]));
        }
        if (featureType == FeatureType.BOOLEAN_TYPE) {
            return new FeatureValue(Boolean.valueOf(values[1]));
        }
        return null;
    }
}
