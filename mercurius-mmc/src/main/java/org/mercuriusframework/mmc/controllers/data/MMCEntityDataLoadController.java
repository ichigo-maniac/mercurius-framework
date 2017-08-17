package org.mercuriusframework.mmc.controllers.data;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.dto.CatalogUniqueCodeEntityDto;
import org.mercuriusframework.dto.EntityDto;
import org.mercuriusframework.dto.UniqueCodeEntityDto;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.CatalogUniqueCodeEntity;
import org.mercuriusframework.entities.UniqueCodeEntity;
import org.mercuriusframework.enums.CriteriaValueType;
import org.mercuriusframework.fillers.impl.CatalogUniqueCodeEntityFiller;
import org.mercuriusframework.fillers.impl.UniqueCodeEntityFiller;
import org.mercuriusframework.mmc.constants.MercuriusMMCConstants;
import org.mercuriusframework.services.EntityReflectionService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.query.CriteriaParameter;
import org.mercuriusframework.services.query.CriteriaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mercurius manager console entity data load controller
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_APPLICATION_PATH)
public class MMCEntityDataLoadController {

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    private EntityReflectionService entityReflectionService;

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    private EntityService entityService;

    /**
     * Catalog unique code entity filler
     */
    @Autowired
    @Qualifier("catalogUniqueCodeEntityFiller")
    private CatalogUniqueCodeEntityFiller catalogUniqueCodeEntityFiller;

    /**
     * Unique code entity filler
     */
    @Autowired
    @Qualifier("uniqueCodeEntityFiller")
    private UniqueCodeEntityFiller uniqueCodeEntityFiller;

    /**
     * Load list of entities by search text
     * @param entityName Entity name
     * @param searchText Search text
     * @return List of entities
     */
    @RequestMapping(method = RequestMethod.GET, value = "/load_entities/{entityName}")
    @ResponseBody
    public List<EntityDto> loadEntities(@PathVariable("entityName") String entityName,
                                        @RequestParam(value = "search_text", required = false, defaultValue = "") String searchText) {
        if (StringUtils.isEmpty(searchText)) {
            return Collections.emptyList();
        }
        Class entityClass = entityReflectionService.getEntityClassByEntityName(entityName);
        if (entityClass == null) {
            return null;
        }
        /** Load entities */
        List<AbstractEntity> entities = entityService.getListResultByCriteria(entityClass, new String[0], buildCriteriaParameters(entityClass, searchText));
        return transformEntities(entityClass, entities);
    }

    /**
     * Build criteria parameters
     * @param entityClass Entity class
     * @param searchText Search text
     * @return Criteria parameters
     */
    private CriteriaParameter[] buildCriteriaParameters(Class entityClass, String searchText) {
        List<CriteriaParameter> parameters = new ArrayList<>();
        if (UniqueCodeEntity.class.isAssignableFrom(entityClass)) {
            parameters.add(new CriteriaParameter(UniqueCodeEntity.CODE, new CriteriaValue(CriteriaValueType.START_WITH, searchText)));
        }
        if (CatalogUniqueCodeEntity.class.isAssignableFrom(entityClass)) {
            parameters.add(new CriteriaParameter(CatalogUniqueCodeEntity.CODE, new CriteriaValue(CriteriaValueType.START_WITH, searchText)));
        }
        return parameters.toArray(new CriteriaParameter[parameters.size()]);
    }

    /**
     * Transform entities
     * @param entityClass Entity class
     * @param entities List of entities
     * @return List of data transfer object
     */
    private List transformEntities(Class entityClass, List<AbstractEntity> entities) {
        if (UniqueCodeEntity.class.isAssignableFrom(entityClass)) {
            List<UniqueCodeEntityDto> result = new ArrayList<>(entities.size());
            for (AbstractEntity entity : entities) {
                UniqueCodeEntityDto dto = new UniqueCodeEntityDto();
                uniqueCodeEntityFiller.fillIn((UniqueCodeEntity) entity, dto);
                result.add(dto);
            }
            return result;
        }
        if (CatalogUniqueCodeEntity.class.isAssignableFrom(entityClass)) {
            List<CatalogUniqueCodeEntityDto> result = new ArrayList<>(entities.size());
            for (AbstractEntity entity : entities) {
                CatalogUniqueCodeEntityDto dto = new CatalogUniqueCodeEntityDto();
                catalogUniqueCodeEntityFiller.fillIn((CatalogUniqueCodeEntity) entity, dto);
                result.add(dto);
            }
            return result;
        } else {
            return Collections.emptyList();
        }
    }
}
