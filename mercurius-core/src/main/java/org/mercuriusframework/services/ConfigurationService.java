package org.mercuriusframework.services;

import java.util.List;

/**
 * Configuration service interface
 */
public interface ConfigurationService {
    /**
     * Get parameter value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    String getParameter(String name);

    /**
     * Get parameter value from properties file
     *
     * @param name         Parameter name
     * @param defaultValue Default parameter value
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    String getParameter(String name, String defaultValue);

    /**
     * Get integer parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    Integer getIntParameter(String name);

    /**
     * Get integer parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    Integer getIntParameter(String name, Integer defaultValue);

    /**
     * Get long parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    Long getLongParameter(String name);

    /**
     * Get long parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    Long getLongParameter(String name, Long defaultValue);

    /**
     * Get boolean parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    Boolean getBooleanParameter(String name);

    /**
     * Get boolean parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    Boolean getBooleanParameter(String name, Boolean defaultValue);

    /**
     * Get set of parameters split by delimiter
     *
     * @param name       Parameter name
     * @param delimiter  Delimiter (if delimiter null - default value is semicolon) (regex)
     * @param trimParams Trim split parameters
     * @return List of parameters (empty set if there is no any parameters with given name)
     */
    List<String> getParameters(String name, String delimiter, boolean trimParams);

    /**
     * Check existing of parameter with given name
     *
     * @param name Parameter name
     * @return Check result
     */
    boolean containParameter(String name);
}
