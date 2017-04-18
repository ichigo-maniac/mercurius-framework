package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.exceptions.UnsupportedImportCollectionException;
import org.mercuriusframework.dataimport.exceptions.WrongImportDataValueFormatException;
import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.exceptions.CatalogUniqueCodeEntityAbsenceException;
import org.mercuriusframework.services.CatalogUniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Multi values catalog unique code entity import bean
 */
@Service("multiCatalogUniqueCodeImportBean")
public class MultiCatalogUniqueCodeImportBean implements ValueImportBean {

    /**
     * Constants
     */
    private static final String SPLITTER = "::";
    private static final String CODES_SPLITTER = ";";

    /**
     * Catalog unique code entity service
     */
    @Autowired
    @Qualifier("catalogUniqueCodeEntityService")
    private CatalogUniqueCodeEntityService catalogUniqueCodeEntityService;

    /**
     * Get value by string
     *
     * @param value     String value
     * @param field        Field
     * @param sourceObject Source object
     * @return Found object
     */
    @Override
    public Object getValueByString(String value, Field field, Object sourceObject) {
        Class classType = field.getType();
        /** Set */
        if (classType.isAssignableFrom(Set.class)) {
            Set<CatalogUniqueCodeEntity> result = new HashSet<>();
            Class entityClass = getCollectionTypeClass(field);
            setDataToCollection(result, value, entityClass);
            return result;
        }
        /** List */
        if (classType.isAssignableFrom(List.class)) {
            List<CatalogUniqueCodeEntity> result = new ArrayList<>();
            Class entityClass = getCollectionTypeClass(field);
            setDataToCollection(result, value, entityClass);
            return result;
        }
        if (!classType.isAssignableFrom(Collection.class)) {
            List<CatalogUniqueCodeEntity> result = new ArrayList<>();
            Class entityClass = field.getType();
            setDataToCollection(result, value, entityClass);
            return result;
        }
        throw new UnsupportedImportCollectionException(classType.getName());
    }

    /**
     * Set data to collection
     * @param collection Collection
     * @param rawValue Raw value
     * @param entityClass Entity class
     */
    private void setDataToCollection(Collection<CatalogUniqueCodeEntity> collection, String rawValue, Class entityClass) {
        String values[] = rawValue.split(SPLITTER);
        if (values.length != 2) {
            throw new WrongImportDataValueFormatException("<catalog_code>" + SPLITTER +
                    "<entity_code_n>" + CODES_SPLITTER + "..." + CODES_SPLITTER + "<entity_code_n>");
        }
        String catalogCode = values[0].trim();
        String entityCodes[] = values[1].split(CODES_SPLITTER);
        for (String entityCode : entityCodes) {
            CatalogUniqueCodeEntity catalogUniqueCodeEntity = catalogUniqueCodeEntityService.getEntityByCodeAndCatalogCode(
                    entityCode.trim(), catalogCode, entityClass);
            if (catalogUniqueCodeEntity != null) {
                collection.add(catalogUniqueCodeEntity);
            } else {
                throw new CatalogUniqueCodeEntityAbsenceException(entityClass.getName(), entityCode.trim(), catalogCode);
            }
        }
    }
}
