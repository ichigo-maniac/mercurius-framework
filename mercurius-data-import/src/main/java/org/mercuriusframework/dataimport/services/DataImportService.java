package org.mercuriusframework.dataimport.services;

/**
 * Data import service interface
 */
public interface DataImportService {
    /**
     * Import data
     * @param xmlStringValue Xml string value
     * @return Log result
     */
    String importData(String xmlStringValue);
}
