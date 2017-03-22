package org.mercuriusframework.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mercuriusframework.services.DataImportService;
import org.springframework.stereotype.Service;

/**
 * Data import service
 */
@Service("dataImportService")
public class DataImportServiceImpl implements DataImportService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getRootLogger();

    /**
     * Import data
     * @param xmlStringValue Xml string value
     * @return Log result
     */
    @Override
    public String importData(String xmlStringValue) {
        return null;
    }
}
