package org.mercuriusframework.test.services;

import org.junit.Test;
import org.mercuriusframework.entities.SolrIndexTaskEntity;
import org.mercuriusframework.services.SolrIndexTaskService;
import org.mercuriusframework.test.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Solr index task service test
 */
public class SolrIndexTaskServiceTest extends AbstractTest {

    /**
     * Constants
     */
    private static final List<String> ALL_TASKS_UUIDS_LIST = Arrays.asList(
            "4a4b636e-f065-11e6-9dac-836adef2f3a6", "224b636e-f065-11e6-9dac-836adef2f3a6",
            "3a4b636e-f065-11e6-9dac-836adef2f3a6"
    );

    private static final List<String> TASKS_UUIDS_LIST = Arrays.asList(
            "4a4b636e-f065-11e6-9dac-836adef2f3a6", "224b636e-f065-11e6-9dac-836adef2f3a6"
    );

    /**
     * Solr index task service
     */
    @Autowired
    private SolrIndexTaskService solrIndexTaskService;

    /**
     * Method test - solrIndexTaskService.getTasks
     */
    @Test
    public void getTasksTest() {
        List<SolrIndexTaskEntity> tasks = solrIndexTaskService.getAllTasks();
        assertUuidListsEquals(ALL_TASKS_UUIDS_LIST, getUuids(tasks));
    }

    /**
     * Method test - solrIndexTaskService.getTasks
     */
    @Test
    public void getTasksTest2() {
        List<SolrIndexTaskEntity> tasks = solrIndexTaskService.getTasks(true);
        assertUuidListsEquals(TASKS_UUIDS_LIST, getUuids(tasks));
    }
}
