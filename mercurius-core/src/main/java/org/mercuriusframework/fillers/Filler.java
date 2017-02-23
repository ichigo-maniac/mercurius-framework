package org.mercuriusframework.fillers;

import org.mercuriusframework.enums.LoadOptions;

import java.util.List;

/**
 * Filler interface
 */
public interface Filler<SOURCE, RESULT> {
    /**
     * Fill a result object from a source object
     * @param source Source object
     * @param result Result object
     * @param options Load options
     */
     void fillIn(SOURCE source, RESULT result, LoadOptions... options);

    /**
     * Fill all result objects from source objects
     * @param sources Source objects
     * @param results Result objects
     * @param options Load options
     */
    default void fillInAll(List<SOURCE> sources, List<RESULT> results, LoadOptions... options) {
        for (int i = 0; i < sources.size(); i++) {
            fillIn(sources.get(i), results.get(i), options);
        }
    }
}
