package org.mercuriusframework.services.query;

import java.util.Collections;
import java.util.List;

/**
 * Pageable result interface
 */
public interface PageableResult<T> {
    /**
     * Get total entries count
     * @return Total entries count
     */
    Integer getTotalEntriesCount();
    /**
     * Get current page
     * @return Current page
     */
    Integer getCurrentPage();
    /**
     * Get pages count
     * @return Pages count
     */
    Integer getPagesCount();
    /**
     * Get page size
     * @return Page size
     */
    Integer getPageSize();
    /**
     * Get entries in the page
     * @return List of entries
     */
    List<T> getEntries();

    /**
     * Return empty pageable result
     * @return Pageable result
     */
    static PageableResult emptyPageableResult() {
        return new DefaultPageableResult<>(0, 0, 0, 0, Collections.emptyList());
    }
}
