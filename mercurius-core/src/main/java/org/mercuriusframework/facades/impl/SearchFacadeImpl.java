package org.mercuriusframework.facades.impl;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.dto.SolrDocumentDto;
import org.mercuriusframework.entities.AbstractEntity;
import org.mercuriusframework.entities.SolrIndexFieldEntity;
import org.mercuriusframework.entities.SolrSearchResolverEntity;
import org.mercuriusframework.enums.LoadOptions;
import org.mercuriusframework.exceptions.SolrSearchResolverAbsenceException;
import org.mercuriusframework.facades.SearchFacade;
import org.mercuriusframework.services.AnnotationService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.services.query.ConvertiblePageableResult;
import org.mercuriusframework.services.query.DefaultPageableResult;
import org.mercuriusframework.services.query.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Search facade
 */
@Service("searchFacade")
@Profile(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)
public class SearchFacadeImpl implements SearchFacade {

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
     * Annotation service
     */
    @Autowired
    @Qualifier("annotationService")
    protected AnnotationService annotationService;

    /**
     * Solr template
     */
    @Autowired
    protected SolrTemplate solrTemplate;

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param page Current page
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    @Override
    public PageableResult search(String solrSearchResolverCode, String textQuery, Integer page, String... fetchFields) {
        SolrSearchResolverEntity solrSearchResolver = uniqueCodeEntityService.getEntityByCodeWithFetch(solrSearchResolverCode,
                SolrSearchResolverEntity.class, SolrSearchResolverEntity.TEXT_SEARCH_FIELDS);
        if (solrSearchResolver == null) {
            throw new SolrSearchResolverAbsenceException(solrSearchResolverCode);
        }
        Page<SolrDocumentDto> solrPage = getDocuments(solrSearchResolver, textQuery, page);
        List<String> uuids = getEntityUuids(solrPage);
        Class entityClass = annotationService.getEntityClassByEntityName(solrSearchResolver.getIndexEntityName());
        List<AbstractEntity> entities = entityService.findByUuids(uuids, entityClass, fetchFields);
        /** Create result page */
        Integer currentPage = calculateCurrentPage(solrSearchResolver.getPageSize(), page, solrPage.getTotalElements());
        return new DefaultPageableResult((int) solrPage.getTotalElements(), currentPage, solrSearchResolver.getPageSize(),
                solrPage.getTotalPages(), entities);
    }

    /**
     * Search indexed data
     * @param solrSearchResolverCode Solr search resolver code
     * @param textQuery              Text query
     * @param page Current page
     * @param converter Converter
     * @param loadOptions Load options
     * @param fetchFields Fetch fields
     * @return Pageable result
     */
    @Override
    public PageableResult search(String solrSearchResolverCode, String textQuery, Integer page, Converter converter,
                                 LoadOptions[] loadOptions, String... fetchFields) {
        PageableResult searchResult = search(solrSearchResolverCode, textQuery, page, fetchFields);
        return new ConvertiblePageableResult(searchResult, converter, loadOptions);
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
     * @return Documents page
     */
    private Page<SolrDocumentDto> getDocuments(SolrSearchResolverEntity resolver, String textQuery, Integer page) {
        Long totalCount = getTotalCount(resolver, textQuery);
        Integer currentPage = calculateCurrentPage(resolver.getPageSize(), page, totalCount);
        Query dataQuery = createQuery(resolver, textQuery);
        dataQuery.setOffset(currentPage * resolver.getPageSize());
        dataQuery.setRows(resolver.getPageSize());
        return solrTemplate.query(resolver.getSolrCollectionName(), dataQuery, SolrDocumentDto.class);
    }

    /**
     * Get total items count
     * @param resolver Solr search resolver
     * @param textQuery Text query
     * @return Items count
     */
    private long getTotalCount(SolrSearchResolverEntity resolver, String textQuery) {
        Query query = createQuery(resolver, textQuery);
        return solrTemplate.count(resolver.getSolrCollectionName(), query);
    }

    /**
     * Create solr query
     * @param resolver Solr search resolver
     * @param textQuery Text query
     * @return Solr query
     */
    private Query createQuery(SolrSearchResolverEntity resolver, String textQuery) {
        Criteria criteria = null;
        /** Build text criteria path */
        if (!resolver.getTextSearchFields().isEmpty()) {
            Criteria textCriteria = null;
            for (SolrIndexFieldEntity textField : resolver.getTextSearchFields()) {
                Criteria tempTextCriteria = new Criteria(textField.getSolrDocumentFieldName());
                if (textField.getCaseInsensitive() != null && textField.getCaseInsensitive()) {
                    tempTextCriteria = tempTextCriteria.contains(textQuery.toLowerCase());
                } else {
                    tempTextCriteria = tempTextCriteria.contains(textQuery);
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
        /** Build other path */
        /** Build query */
        if (criteria != null) {
            return new SimpleQuery(criteria);
        } else {
            return new SimpleQuery();
        }
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

    /**
     * Get pages count
     * @param pageSize Page size
     * @param totalCount Total entries count
     * @return Pages count
     */
    private Integer getPagesCount(Integer pageSize, Long totalCount) {
        if (totalCount == 0) {
            return 0;
        } else {
            return (int) ((totalCount - 1) / pageSize) + 1;
        }
    }
}
