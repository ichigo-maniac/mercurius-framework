package org.mercuriusframework.entities.converters;

import org.mercuriusframework.entities.attributes.FeatureValue;
import org.mercuriusframework.enums.FeatureType;
import org.mercuriusframework.enums.FeatureValueEnum;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Feature value converter
 */
@Convert
public class FeatureValueConverter implements AttributeConverter<FeatureValue, String> {
    /**
     * Splitter
     */
    private static final String SPLITTER = ":::";

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
        if (featureType == FeatureType.ENUM_TYPE) {
            FeatureValueEnum enumValue = (FeatureValueEnum) featureValue.getValue();
            return featureType.getValue() + SPLITTER + enumValue.getFullClassName() + "(" + enumValue.getValue() + ")";
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
        if (featureType == FeatureType.ENUM_TYPE) {
            String fullClassName = values[1].substring(0, values[1].indexOf("(") - 1);
            try {
                Class className = Class.forName(fullClassName);
                if (className.isAssignableFrom(FeatureValueEnum.class)) {
                    String enumStringValue = values[1].substring(values[1].indexOf("("), values[1].indexOf(")") - 1);
                    Method fromStringMethod = className.getMethod(FeatureValueEnum.VALUE_FROM_STRING_METHOD, String.class);
                    FeatureValueEnum value = (FeatureValueEnum) fromStringMethod.invoke(null, enumStringValue);
                    return new FeatureValue(value);
                }
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            } catch (NoSuchMethodException exception) {
                exception.printStackTrace();
            } catch (IllegalAccessException exception) {
                exception.printStackTrace();
            } catch (InvocationTargetException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
