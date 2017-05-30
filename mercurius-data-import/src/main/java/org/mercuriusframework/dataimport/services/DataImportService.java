package org.mercuriusframework.dataimport.services;

import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

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

    /**
     * Import data
     * @param file Import data file
     * @return Log result
     */
    String importData(MultipartFile file);

    /**
     * Import data
     * @param stream Import stream
     * @return Log result
     */
    String importData(InputStream stream);

    /**
     * Import classpath data
     * @param path Resource path
     * @return Log result
     */
    String importClasspathData(String path);
}
