package org.mercuriusframework.fillers.impl;

import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.fillers.Filler;
import org.mercuriusframework.dto.EntityDto;
import org.mercuriusframework.entities.AbstractEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Abstract entity filler
 */
@Service("abstractEntityFiller")
public class AbstractEntityFiller<SOURCE extends AbstractEntity, RESULT extends EntityDto> implements Filler<SOURCE, RESULT> {
    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     * @param options Load options
     */
    @Override
    public  void fillIn(SOURCE source, RESULT result, LoadOptions... options) {
        result.setUuid(source.getUuid());
        result.setCreationTime(source.getCreationTime() == null ? null : new Date(source.getCreationTime().getTime()));
        result.setModificationTime(source.getModificationTime() == null ? null : new Date(source.getModificationTime().getTime()));
    }

}
