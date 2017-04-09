package org.mercuriusframework.facades.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import java.io.IOException;
import java.util.*;

/**
 * Wildcard reloadable resource bundle message source
 */
public class WildcardReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    /**
     * Constants
     */
    private static final String LOCALIZATION_RESOURCE_FOLDER = "localization";
    private static final String BASENAME_REGEX = "classpath*:**localization/**-labels*";
    private static final String BASENAME_PREFIX = "classpath:localization";
    private static final String PROPERTIES_EXTENSION = ".properties";

    /**
     * Resource path resolver
     */
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    /**
     * Constructor
     */
    public WildcardReloadableResourceBundleMessageSource() {
        Set<String> baseNames = new HashSet<>();
        try {
            Resource[] resources = resourcePatternResolver.getResources(BASENAME_REGEX);
            for (Resource resource : resources) {
                String uri = resource.getURI().toString();
                String baseName = null;
                if (resource instanceof FileSystemResource) {
                    baseName = BASENAME_PREFIX + StringUtils.substringBetween(uri, LOCALIZATION_RESOURCE_FOLDER, PROPERTIES_EXTENSION);
                } else if (resource instanceof ClassPathResource) {
                    baseName = StringUtils.substringBefore(uri, PROPERTIES_EXTENSION);
                } else if (resource instanceof UrlResource) {
                    baseName = BASENAME_PREFIX + StringUtils.substringBetween(uri, LOCALIZATION_RESOURCE_FOLDER, PROPERTIES_EXTENSION);
                }
                if (StringUtils.isNotEmpty(baseName)) {
                    baseName = baseName.replaceFirst("labels_([a-zA-Z]{2,3})$", "labels");
                    baseNames.add(baseName);
                }

            }
        } catch (IOException e) {
        }

        super.setBasenames(baseNames.toArray(new String[baseNames.size()]));
    }
}
