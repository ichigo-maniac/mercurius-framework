package org.mercuriusframework.dataimport.services.impl.importbeans;

import org.mercuriusframework.dataimport.services.ValueImportBean;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.exceptions.UniqueCodeEntityAbsenceException;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * Unique code entity import bean
 */
@Service("uniqueCodeImportBean")
public class UniqueCodeImportBean implements ValueImportBean {

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Find value by string
     *
     * @param value     String value
     * @param setMethod Set method
     * @return Found object
     */
    @Override
    public Object findValueByString(String value, Method setMethod) {
        Class classType = setMethod.getParameterTypes()[0];
        UniqueCodeEntity uniqueCodeEntity = uniqueCodeEntityService.getEntityByCode(value.trim(), classType);
        if (uniqueCodeEntity != null) {
            return uniqueCodeEntity;
        } else {
            throw new UniqueCodeEntityAbsenceException(classType.getName(), value.trim());
        }
    }
}
