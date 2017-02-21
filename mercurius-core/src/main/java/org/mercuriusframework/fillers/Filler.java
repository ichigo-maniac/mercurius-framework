package org.mercuriusframework.fillers;

import java.util.List;

/**
 * Filler interface
 */
public interface Filler<SOURCE, RESULT> {
    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     */
     void fillIn(SOURCE source, RESULT result);

    /**
     * Fill all result objects from source objects
     * @param sources Source objects
     * @param results Result objects
     */
    default void fillInAll(List<SOURCE> sources, List<RESULT> results) {
        for (int i = 0; i < sources.size(); i++) {
            fillIn(sources.get(i), results.get(i));
        }
    }
}
