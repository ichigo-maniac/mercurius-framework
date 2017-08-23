package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.SolrSearchResolverEntity;
import org.mercuriusframework.entities.SolrSortEntity;
import org.mercuriusframework.services.SolrSortService;
import org.mercuriusframework.services.UniqueCodeEntityService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Solr sort service test
 */
public class SolrSortServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> SORTS_UUIDS_LIST = Arrays.asList(
            "1111b636e-0277-11e6-5322-836adef2234", "1111b636e-0277-11e6-5322-836a5322234"
    );

    /**
     * Solr sort service
     */
    @Autowired
    private SolrSortService solrSortService;

    /**
     * Unique code entity service
     */
    @Autowired
    private UniqueCodeEntityService uniqueCodeEntityService;

    /**
     * Method test - solrSortService.
     */
    @Test
    public void getSortsBySolrSearchResolverUuidTest() {
        SolrSearchResolverEntity resolverEntity = uniqueCodeEntityService.getEntityByCode("dummy_solr_search_resolver", SolrSearchResolverEntity.class);
        List<SolrSortEntity> sorts = solrSortService.getSortsBySolrSearchResolver(resolverEntity);
        assertUuidListsEquals(SORTS_UUIDS_LIST, getUuids(sorts));
    }

    /**
     * Method test - solrSortService.
     */
    @Test
    public void getSortsBySolrSearchResolverCodeTest() {
        List<SolrSortEntity> sorts = solrSortService.getSortsBySolrSearchResolverCode("dummy_solr_search_resolver");
        assertUuidListsEquals(SORTS_UUIDS_LIST, getUuids(sorts));
    }

    /**
     * Method test - solrSortService.
     */
    @Test
    public void getSortsBySolrSearchResolverTest() {
        List<SolrSortEntity> sorts = solrSortService.getSortsBySolrSearchResolverUuid("4a9b636e-f065-0000-9dac-836adef2f111");
        assertUuidListsEquals(SORTS_UUIDS_LIST, getUuids(sorts));
    }
}
