package org.mercuriusframework.converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter interface
 */
public interface Converter<SOURCE, RESULT> {
    /**
     * Convert a source object to a result object
     * @param source Source object
     * @return Result object
     */
    RESULT convert(SOURCE source);

    /**
     * Convert source object to result objects
     * @param sources Source objects
     * @return Result objects
     */
    default List<RESULT> convertAll(List<SOURCE> sources) {
        List<RESULT> results = new ArrayList<>(sources.size());
        for (int i = 0; i < sources.size(); i++) {
            results.add(convert(sources.get(i)));
        }
        return results;
    }
}
