package org.mercuriusframework.services.query;

import java.util.ArrayList;
import java.util.List;

import org.mercuriusframework.converters.Converter;
import org.mercuriusframework.enums.LoadOptions;

/**
 * Convertible pageable result
 */
public class ConvertiblePageableResult<SOURCE, TARGET> extends DefaultPageableResult implements PageableResult {
    /**
     * Constructor
     * @param result Pageable result
     * @param converter Object converter
     * @param loadOptions Load options
     */
     public <SOURCE, TARGET> ConvertiblePageableResult(PageableResult<SOURCE> result, Converter<SOURCE, TARGET> converter, LoadOptions... loadOptions) {
        this.totalEntriesCount = result.getTotalEntriesCount();
        this.currentPage = result.getCurrentPage();
        this.pageSize = result.getPageSize();
        this.pagesCount = result.getPagesCount();
        entries = converter.convertAll(result.getEntries(), loadOptions);
    }

    /**
     * Constructor
     * @param totalEntriesCount Total entries count
     * @param currentPage Current page
     * @param pageSize Pages count
     * @param pagesCount Page size
     * @param entries Entries
     * @param converter Converter
     * @param loadOptions Load options
     */
    public <SOURCE, TARGET> ConvertiblePageableResult(Integer totalEntriesCount, Integer currentPage, Integer pageSize, Integer pagesCount, List<SOURCE> entries,
                                                      Converter<SOURCE, TARGET> converter, LoadOptions... loadOptions) {
        this.totalEntriesCount = totalEntriesCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pagesCount = pagesCount;
        this.entries = converter.convertAll(entries, loadOptions);
    }
}
