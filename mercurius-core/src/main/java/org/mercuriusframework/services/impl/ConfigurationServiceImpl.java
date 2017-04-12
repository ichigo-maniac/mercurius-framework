package org.mercuriusframework.services.impl;

import com.google.common.base.Splitter;
import org.apache.commons.lang.ArrayUtils;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import javax.servlet.ServletContext;
import java.util.*;

/**
 * Configuration service (retrieve parameters from application.properties file in classpath)
 */
@PropertySource(MercuriusConstants.COMMON.APPLICATION_PROPERTIES_PATH)
@Service("configurationService")
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class ConfigurationServiceImpl implements ConfigurationService, ServletContextAware {
    /**
     * Server root path
     */
    private String serverRoot;

    /**
     * Spring environment bean
     */
    @Autowired
    protected Environment environment;

    /**
     * Get server root path
     *
     * @return Server root path
     */
    public String getServerRoot() {
        return serverRoot;
    }

    /**
     * Get parameter value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    public String getParameter(String name) {
        return environment.getProperty(name);
    }

    /**
     * Get parameter value from properties file
     *
     * @param name         Parameter name
     * @param defaultValue Default parameter value
     * @return Parameter value (@param defaultValue if there is no any parameters with given name)
     */
    public String getParameter(String name, String defaultValue) {
        return environment.getProperty(name, defaultValue);
    }

    /**
     * Get integer parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    public Integer getIntParameter(String name) {
        return environment.getProperty(name, Integer.class);
    }

    /**
     * Get integer parameter  value from properties file
     *
     * @param name         Parameter name
     * @param defaultValue
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    public Integer getIntParameter(String name, Integer defaultValue) {
        return environment.getProperty(name, Integer.class, defaultValue);
    }

    /**
     * Get long parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    public Long getLongParameter(String name) {
        return environment.getProperty(name, Long.class);
    }

    /**
     * Get long parameter  value from properties file
     *
     * @param name         Parameter name
     * @param defaultValue
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    public Long getLongParameter(String name, Long defaultValue) {
        return environment.getProperty(name, Long.class, defaultValue);
    }

    /**
     * Get boolean parameter  value from properties file
     *
     * @param name Parameter name
     * @return Parameter value (null if there is no any parameters with given name)
     */
    public Boolean getBooleanParameter(String name) {
        return environment.getProperty(name, Boolean.class);
    }

    /**
     * Get boolean parameter  value from properties file
     *
     * @param name         Parameter name
     * @param defaultValue
     * @return Parameter value (defaultValue if there is no any parameters with given name)
     */
    public Boolean getBooleanParameter(String name, Boolean defaultValue) {
        return environment.getProperty(name, Boolean.class, defaultValue);
    }

    /**
     * Get set of parameters split by delimiter
     *
     * @param name       Parameter name
     * @param delimiter  Delimiter (if delimiter null - default value is semicolon) (regex)
     * @param trimParams Trim split parameters
     * @return List of parameters (empty set if there is no any parameters with given name)
     */
    public List<String> getParameters(String name, String delimiter, boolean trimParams) {
        String rawValue = getParameter(name);
        if (rawValue == null) {
            return Collections.emptyList();
        }
        if (delimiter == null) {
            return Collections.emptyList();
        }
        Splitter splitter = Splitter.on(delimiter).omitEmptyStrings();
        if (trimParams) {
            splitter = splitter.trimResults();
        }
        return splitter.splitToList(rawValue);
    }

    /**
     * Check existing of parameter with given name
     * @param name Parameter name
     * @return Check result
     */
    public boolean containParameter(String name) {
        return environment.containsProperty(name);
    }

    /**
     * Get active profiles
     * @return Array of active profiles
     */
    @Override
    public String[] getActiveProfiles() {
        return environment.getActiveProfiles();
    }

    /**
     * Check is profile active
     * @param profile Profile
     * @return Check result
     */
    @Override
    public boolean isProfileActive(String profile) {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles != null) {
            return ArrayUtils.contains(activeProfiles, profile);
        } else {
            return false;
        }
    }

    /**
     * Set servlet context
     *
     * @param servletContext Servlet context
     */
    public void setServletContext(ServletContext servletContext) {
        serverRoot = servletContext.getRealPath("/").replace("\\", "/");
    }
}
