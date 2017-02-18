package org.mercuriusframework.test;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mercuriusframework.configuration.test.TestDatabaseConnectionConfiguration;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.entities.AbstractEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDatabaseConnectionConfiguration.class})
@ActiveProfiles(MercuriusConstants.PROFILES.TEST_PROFILE)
public abstract class AbstractTest extends Assert {


    /**
     * Get entities uuids
     * @param entities List of entities
     * @return List of uuids
     */
    protected <T extends AbstractEntity> List<String> getUuids(List<T> entities) {
        List<String> result = new ArrayList<String>(entities.size());
        for (T entity : entities) {
            result.add(entity.getUuid());
        }
        return result;
    }

    /***
     * Assert uuid lists equals
     * @param firstList First list of uuids
     * @param secondList Second list of uuids
     */
    protected void assertOrderedUuidListsEquals(List<String> firstList, List<String> secondList) {
        if (firstList.size() != secondList.size()) {
            assertEquals("First list doesn't have same size as second list", firstList.size(), secondList.size());
        }
        boolean areListsEqual = true;
        for (int i = 0; i < firstList.size(); i++) {
            if (!firstList.get(i).equals(secondList.get(i))) {
                areListsEqual = false;
            }
        }
        assertEquals("Lists are not equal", areListsEqual, true);
    }

    /***
     * Assert uuid lists equals
     * @param firstList First list of uuids
     * @param secondList Second list of uuids
     */
    protected void assertUuidListsEquals(List<String> firstList, List<String> secondList) {
        if (firstList.size() != secondList.size()) {
            assertEquals("First list doesn't have same size as second list", firstList.size(), secondList.size());
        }
        Collections.sort(firstList);
        Collections.sort(secondList);
        assertArrayEquals("Lists are not equal", firstList.toArray(), secondList.toArray());
    }
}
