package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.exceptions.WrongImportDataValueFormatException;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.BigStringEntity;
import org.mercuriusframework.entities.CatalogEntity;
import org.mercuriusframework.exceptions.CatalogAbsenceException;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Big string entity import bean
 */
@Service("bigStringImportBean")
public class BigStringImportBean implements ValueImportBean {

    /**
     * Constants
     */
    private static final String SPLITTER = "::";

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        String values[] = value.split(SPLITTER);
        if (values.length != 2) {
            throw new WrongImportDataValueFormatException("<catalog_code>" + SPLITTER + "<big_string_value>");
        }
        /** Load catalog */
        CatalogEntity catalog = uniqueCodeEntityService.getEntityByCode(values[0].trim(), CatalogEntity.class);
        if (catalog == null) {
            throw new CatalogAbsenceException(values[0].trim());
        }
        return new BigStringEntity(values[1].trim(), catalog);
    }
}
