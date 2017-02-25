package org.mercuriusframework.services.query;

import java.util.List;

/**
 * Default pageable result implementation
 */
public class DefaultPageableResult<T> implements PageableResult {
    /**
     * Total entries count
     */
    protected Integer totalEntriesCount;
    /**
     * Current page
     */
    protected Integer currentPage;
    /**
     * Pages count
     */
    protected Integer pagesCount;
    /**
     * Page size
     */
    protected Integer pageSize;
    /**
     * Entries
     */
    protected List<T> entries;

    /**
     * Default constructor
     */
    public DefaultPageableResult() {
    }

    /**
     * Constructor
     * @param totalEntriesCount Total entries count
     * @param currentPage Current page
     * @param pageSize Pages count
     * @param pagesCount Page size
     * @param entries Entries
     */
    public DefaultPageableResult(Integer totalEntriesCount, Integer currentPage, Integer pageSize, Integer pagesCount, List<T> entries) {
        this.totalEntriesCount = totalEntriesCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pagesCount = pagesCount;
        this.entries = entries;
    }

    /**
     * Get total entries count
     * @return Total entries count
     */
    @Override
    public Integer getTotalEntriesCount() {
        return totalEntriesCount;
    }

    /**
     * Get current page
     * @return Current page
     */
    @Override
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * Get pages count
     * @return Pages count
     */
    @Override
    public Integer getPagesCount() {
        return pagesCount;
    }

    /**
     * Get page size
     * @return Page size
     */
    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Get entries in the page
     * @return List of entries
     */
    @Override
    public List<T> getEntries() {
        return entries;
    }
}
