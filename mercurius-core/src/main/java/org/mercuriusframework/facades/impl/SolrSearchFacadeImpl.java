package org.mercuriusframework.facades.impl;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.Range;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.SolrDocumentDto;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.entities.SolrSearchResolverEntity;
import org.mercuriusframework.entities.SolrSortEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.enums.SolrCriteriaValueType;
import org.mercuriusframework.enums.SolrSortDirectionType;
import org.mercuriusframework.exceptions.SolrSearchResolverAbsenceException;
import org.mercuriusframework.facades.SolrSearchFacade;
import org.mercuriusframework.facades.solr.SolrCriteriaParameter;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.mercuriusframework.services.EntityReflectionService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.query.ConvertiblePageableResult;
import org.mercuriusframework.services.query.DefaultPageableResult;
import org.mercuriusframework.services.query.PageableResult;
import org.mercuriusframework.services.solr.SolrFieldResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Search facade
 */
@Service("solrSearchFacade")
@Profile(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)
public class SolrSearchFacadeImpl implements SolrSearchFacade {

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    protected EntityService entityService;

    /**
     * Unique code entity service
     */
    @Autowired
    @Qualifier("uniqueCodeEntityService")
    protected UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Entity reflection service
     */
    @Autowired
    @Qualifier("entityReflectionService")
    protected EntityReflectionService entityReflectionService;

    /**
     * Solr template
     */
    @Autowired
    protected SolrTemplate solrTemplate;

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param parameters Solr criteria parameters
     * @param page Current page
     * @param solrSort Solr sort
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    @Override
    public PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters,
                                 Integer page, SolrSortEntity solrSort, String... fetchFields) {
        SolrSearchResolverEntity solrSearchResolver = uniqueCodeEntityService.getEntityByCodeWithFetch(solrSearchResolverCode,
                SolrSearchResolverEntity.class, SolrSearchResolverEntity.TEXT_SEARCH_FIELDS);
        if (solrSearchResolver == null) {
            throw new SolrSearchResolverAbsenceException(solrSearchResolverCode);
        }
        Page<SolrDocumentDto> solrPage = getDocuments(solrSearchResolver, textQuery, solrSort, parameters, page);
        List<String> uuids = getEntityUuids(solrPage);
        Class entityClass = entityReflectionService.getEntityClassByEntityName(solrSearchResolver.getIndexEntityName());
        List<AbstractEntity> entities = sortByUuids(uuids, entityService.findByUuids(uuids, entityClass, fetchFields));
        /** Create result page */
        Integer currentPage = calculateCurrentPage(solrSearchResolver.getPageSize(), page, solrPage.getTotalElements());
        return new DefaultPageableResult((int) solrPage.getTotalElements(), currentPage, solrSearchResolver.getPageSize(),
                solrPage.getTotalPages(), entities);
    }

    /**
     * Sort entities by uuids
     * @param uuids Uuid list
     * @param entities Entities list
     * @return Sorted entities list
     */
    private List<AbstractEntity> sortByUuids(List<String> uuids, List<AbstractEntity> entities) {
        List<AbstractEntity> result = new ArrayList<>(entities.size());
        Map<String, AbstractEntity> entitiesMap = new HashMap<>();
        for (AbstractEntity entity : entities) {
            entitiesMap.put(entity.getUuid(), entity);
        }
        for (String uuid : uuids) {
            result.add(entitiesMap.get(uuid));
        }
        return result;
    }

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param parameters             Solr criteria parameters
     * @param page                   Current page
     * @param solrSortCode           Solr sort code
     * @param fetchFields            Fetch fields
     * @return Pageable result
     */
    @Override
    public PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters, Integer page, String solrSortCode, String... fetchFields) {
        SolrSortEntity solrSortEntity = uniqueCodeEntityService.getEntityByCode(solrSortCode, SolrSortEntity.class);
        return search(solrSearchResolverCode, textQuery, parameters, page, solrSortEntity, fetchFields);
    }

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param parameters Solr criteria parameters
     * @param page Current page
     * @param solrSort Solr sort
     * @param converter Converter
     * @param loadOptions Load options
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    @Override
    public PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters,
                                 Integer page, SolrSortEntity solrSort, Converter converter,
                                 LoadOptions[] loadOptions, String... fetchFields) {
        PageableResult searchResult = search(solrSearchResolverCode, textQuery, parameters, page, solrSort, fetchFields);
        return new ConvertiblePageableResult(searchResult, converter, loadOptions);
    }

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param parameters             Solr criteria parameters
     * @param page                   Current page
     * @param solrSortCode           Solr sort code
     * @param converter              Converter
     * @param loadOptions            Load options
     * @param fetchFields            Fetch fields
     * @return Pageable result
     */
    @Override
    public PageableResult search(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter[] parameters, Integer page, String solrSortCode, Converter converter, LoadOptions[] loadOptions, String... fetchFields) {
        SolrSortEntity solrSortEntity = uniqueCodeEntityService.getEntityByCode(solrSortCode, SolrSortEntity.class);
        return search(solrSearchResolverCode, textQuery, parameters, page, solrSortEntity, converter, loadOptions, fetchFields);
    }

    /**
     * Return documents count
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param parameters             Solr criteria parameters
     * @return Documents count
     */
    @Override
    public Long count(String solrSearchResolverCode, String textQuery, SolrCriteriaParameter... parameters) {
        SolrSearchResolverEntity solrSearchResolver = uniqueCodeEntityService.getEntityByCodeWithFetch(solrSearchResolverCode,
                SolrSearchResolverEntity.class, SolrSearchResolverEntity.TEXT_SEARCH_FIELDS);
        if (solrSearchResolver == null) {
            throw new SolrSearchResolverAbsenceException(solrSearchResolverCode);
        }
        return getTotalCount(solrSearchResolver, textQuery, parameters);
    }

    /**
     * Get entity uuids
     * @param solrPage Solr page
     * @return List of uuids
     */
    private List<String> getEntityUuids(Page<SolrDocumentDto> solrPage) {
        List<String> result = new ArrayList<>(solrPage.getContent().size());
        for (SolrDocumentDto documentDto : solrPage.getContent()) {
            result.add(documentDto.getId());
        }
        return result;
    }

    /**
     * Get documents
     * @param resolver Solr search resolver
     * @param textQuery Text query
     * @param solrSort Solr sort
     * @param parameters Solr criteria parameters
     * @return Documents page
     */
    private Page<SolrDocumentDto> getDocuments(SolrSearchResolverEntity resolver, String textQuery, SolrSortEntity solrSort, SolrCriteriaParameter[] parameters, Integer page) {
        Long totalCount = getTotalCount(resolver, textQuery, parameters);
        Integer currentPage = calculateCurrentPage(resolver.getPageSize(), page, totalCount);
        Query dataQuery = createQuery(resolver, textQuery, solrSort, parameters);
        dataQuery.setOffset(currentPage * resolver.getPageSize());
        dataQuery.setRows(resolver.getPageSize());
        return solrTemplate.query(resolver.getSolrCollectionName(), dataQuery, SolrDocumentDto.class);
    }

    /**
     * Get total items count
     * @param resolver Solr search resolver
     * @param textQuery Text query
     * @param parameters Solr criteria parameters
     * @return Items count
     */
    private long getTotalCount(SolrSearchResolverEntity resolver, String textQuery, SolrCriteriaParameter[] parameters) {
        Query query = createQuery(resolver, textQuery, null, parameters);
        return solrTemplate.count(resolver.getSolrCollectionName(), query);
    }

    /**
     * Create solr query
     * @param resolver Solr search resolver
     * @param textQuery Text query
     * @param solrSort Solr sort
     * @param parameters Solr criteria parameters
     * @return Solr query
     */
    private Query createQuery(SolrSearchResolverEntity resolver, String textQuery, SolrSortEntity solrSort, SolrCriteriaParameter[] parameters) {
        Criteria criteria = null;
        /** Build text criteria path */
        if (StringUtils.isNotEmpty(textQuery)) {
            if (!resolver.getTextSearchFields().isEmpty()) {
                Criteria textCriteria = null;
                for (SolrIndexFieldEntity textField : resolver.getTextSearchFields()) {
                    Criteria tempTextCriteria = new Criteria(textField.getSolrDocumentFieldName());
                    if (textField.getCaseInsensitive() != null && textField.getCaseInsensitive()) {
                        tempTextCriteria = tempTextCriteria.contains(textQuery.toLowerCase().split(" "));
                    } else {
                        tempTextCriteria = tempTextCriteria.contains(textQuery.split(" "));
                    }
                    /** Add temp text criteria to text criteria */
                    if (textCriteria == null) {
                        textCriteria = tempTextCriteria;
                    } else {
                        textCriteria = textCriteria.or(tempTextCriteria);
                    }
                }
                criteria = textCriteria;
            }
        }
        /** Build other path */
        if (!ArrayUtils.isEmpty(parameters)) {
            for (SolrCriteriaParameter criteriaParameter : parameters) {
                if (criteria != null) {
                    criteria = criteria.and(createCriteria(criteriaParameter));
                } else {
                    criteria = createCriteria(criteriaParameter);
                }
            }
        }
        /** Build query */
        SimpleQuery query = null;
        if (criteria != null) {
            query = new SimpleQuery(criteria);
        } else {
            query = new SimpleQuery();
        }
        /** Add sort */
        if (solrSort != null) {
            if (solrSort.getSolrField() != null) {
                query.addSort(new Sort(SolrSortDirectionType.transformDirection(solrSort.getDirectionType()), solrSort.getSolrField()));
            } else {
                SolrFieldResolver solrFieldResolver = ApplicationContextProvider.getBean(solrSort.getSolrFieldResolver(), SolrFieldResolver.class);
                query.addSort(new Sort(SolrSortDirectionType.transformDirection(solrSort.getDirectionType()), solrFieldResolver.resolveField()));
            }
        }
        return query;
    }

    /**
     * Create criteria
     * @param criteriaParameter Criteria parameter
     * @return Criteria
     */
    private Criteria createCriteria(SolrCriteriaParameter criteriaParameter) {
        Criteria result = new Criteria(criteriaParameter.getSolrField());
        /** Is */
        if (criteriaParameter.getType() == SolrCriteriaValueType.IS) {
            return result.is(criteriaParameter.getValue());
        }
        /** In */
        if (criteriaParameter.getType() == SolrCriteriaValueType.IN) {
            if (criteriaParameter.getValue().getClass().isArray()) {
                return result.in((Object[]) criteriaParameter.getValue());
            } else {
                return result.in((Collection) criteriaParameter.getValue());
            }
        }
        /** Between */
        if (criteriaParameter.getType() == SolrCriteriaValueType.BETWEEN) {
            Range range = (Range) criteriaParameter.getValue();
            return result.between(range.getMinimumNumber(), range.getMaximumNumber());
        }
        return null;
    }

    /**
     * Calculate current page
     * @param pageSize Page size
     * @param currentPage Current page
     * @param totalCount Total entries count
     * @return Current Page
     */
    private Integer calculateCurrentPage(Integer pageSize, Integer currentPage, Long totalCount) {
        if (currentPage == null || currentPage <= 0) {
            return 0;
        }
        Long lastPage = ((totalCount- 1) / pageSize);
        if (currentPage > lastPage) {
            return lastPage.intValue();
        } else {
            return currentPage;
        }
    }
}
