package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.exceptions.WrongImportDataValueFormatException;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Date-time import bean
 */
@Service("dateTimeImportBean")
public class DateTimeImportBean implements ValueImportBean {

    /**
     * Constants
     */
    private static final String SPLITTER = "::";

    /**
     * Find value by string
     * @param value     String value
     * @param setMethod Set method
     * @return Found object
     */
    @Override
    public Object findValueByString(String value, Method setMethod) {
        String values[] = value.split(SPLITTER);
        if (values.length != 2) {
            throw new WrongImportDataValueFormatException("<date_time_format>" + SPLITTER + "<date_time_string>");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(values[0].trim());
        try {
            return dateFormat.parse(values[1].trim());
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }
}
