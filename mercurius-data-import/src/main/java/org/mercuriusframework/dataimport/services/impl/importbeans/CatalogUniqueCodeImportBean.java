package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.exceptions.WrongImportDataValueFormatException;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.exceptions.CatalogUniqueCodeEntityAbsenceException;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Catalog unique code entity import bean
 */
@Service("catalogUniqueCodeImportBean")
public class CatalogUniqueCodeImportBean implements ValueImportBean {

    /**
     * Constants
     */
    private static final String SPLITTER = "::";

    /**
     * Catalog unique code entity service
     */
    @Autowired
    @Qualifier("catalogUniqueCodeEntityService")
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Get value by string
     * @param value        String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        Class classType = field.getType();
        String values[] = value.split(SPLITTER);
        if (values.length != 2) {
            throw new WrongImportDataValueFormatException("<catalog_code>" + SPLITTER + "<entity_code>");
        }
        CatalogUniqueCodeEntity catalogUniqueCodeEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(
                values[1].trim(), values[0].trim(), classType);
        if (catalogUniqueCodeEntity != null) {
            return catalogUniqueCodeEntity;
        } else {
            throw new CatalogUniqueCodeEntityAbsenceException(classType.getName(), values[1].trim(), values[0].trim());
        }
    }
}
