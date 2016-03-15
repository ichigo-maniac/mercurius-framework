package org.mercuriusframework.flyway;

import com.googlecode.flyway.core.Flyway;
import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.services.ConfigurationService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.persistence.PersistenceException;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Flyway database migration
 */
public class DatabaseMigration implements InitializingBean {
    /**
     * Migration scripts directory path (resources)
     */
    private static final String MIGRATION_SCRIPTS_DIR = "flyway_sql_migration";
    /**
     * Database source
     */
    private DataSource dataSource;
    /**
     * Configuration service
     */
    private ConfigurationService configurationService;
    /**
     * JDBC template
     */
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * After property set
     *
     * @throws Exception Exception
     */
    public void afterPropertiesSet() throws Exception {
        boolean validateVersion = configurationService.getBooleanParameter(MercuriusConfigurationParameters.DATABASE_FLYWAY_MIGRATION_PARAMETERS.VALIDATE_VERSION, true);
        boolean cleanSchema = configurationService.getBooleanParameter(MercuriusConfigurationParameters.DATABASE_FLYWAY_MIGRATION_PARAMETERS.CLEAN_SCHEMA, true);
        if (!cleanSchema && validateVersion) {
            validateVersions();
        }
        /** Migrate */
        Flyway flyway = new Flyway();
        flyway.setInitOnMigrate(true);
        flyway.setOutOfOrder(configurationService.getBooleanParameter(MercuriusConfigurationParameters.DATABASE_FLYWAY_MIGRATION_PARAMETERS.OUT_OF_ORDER, true));
        flyway.setDataSource(dataSource);
        /** Location */
        String customFolder = configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_FLYWAY_MIGRATION_PARAMETERS.CUSTOM_SCRIPTS_FOLDER);
        String baseScriptsFolder = DatabaseTypeHelper.getScriptsFolderName(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DRIVER_CLASS_NAME));
        if (customFolder != null) {
            flyway.setLocations(MIGRATION_SCRIPTS_DIR + "/" + baseScriptsFolder, customFolder);
        } else {
            flyway.setLocations(MIGRATION_SCRIPTS_DIR + "/" + baseScriptsFolder);
        }
        /** Clean database */
        if (cleanSchema) {
            flyway.clean();
        }
        flyway.migrate();
    }

    /**
     * Validate current database version
     */
    private void validateVersions() throws IOException {
        Set<String> serverScripts = findFlywayScriptsOnServer();
        if (serverScripts == null) {
            throw new RuntimeException("There is no directory with migration scripts");
        }
        List<String> dbScripts = null;
        try {
            dbScripts = getExecutedScriptNames();
        } catch (PersistenceException exception) {
            Throwable exceptionWrapper = exception.getCause();
            if (exceptionWrapper == null) {
                throw exception;
            }
            Throwable parentExceptionWrapper = exceptionWrapper.getCause();
            if (parentExceptionWrapper == null || !(parentExceptionWrapper instanceof SQLSyntaxErrorException)) {
                throw exception;
            }
            SQLSyntaxErrorException sqlSyntaxErrorException = (SQLSyntaxErrorException) parentExceptionWrapper;
            if (sqlSyntaxErrorException.getSQLState().equals("42000")) {
                throw new RuntimeException("There is no table with name schema_version.");
            }
            throw exception;
        }
        /** Check scripts */
        StringBuilder builder = new StringBuilder();
        for (String script : dbScripts) {
            if (!serverScripts.contains(script))
                builder.append("\n\t" + script);
        }
        final String extraScripts = builder.toString();
        if (!extraScripts.isEmpty())
            throw new RuntimeException("\nThe current database has launched a new version of the server. " +
                    "\nFile list :" + extraScripts);
    }

    /**
     * Get executed script names
     *
     * @return List of script names
     */
    private List<String> getExecutedScriptNames() {
        return jdbcTemplate.queryForList("SELECT script FROM schema_version WHERE type <> 'INIT'", new HashMap<String, Object>(), String.class);
    }

    /**
     * Find flyway migration scripts on server
     *
     * @return List of script names
     * @throws IOException Exception
     */
    private Set<String> findFlywayScriptsOnServer() throws IOException {
        Set<String> coreScripts = getCoreScripts();
        if (coreScripts == null) {
            throw new RuntimeException("Problem with fetching mercurius core scripts");
        }
        Set<String> customScripts = getCustomScripts();
        if (customScripts == null) {
            throw new RuntimeException("Problem with fetching custom scripts");
        }
        /** Create result set */
        Set<String> result = new HashSet<String>(coreScripts);
        result.addAll(customScripts);
        return result;
    }

    /**
     * Get mercurius core scripts
     *
     * @return Set of scripts
     */
    private Set<String> getCoreScripts() {
        String baseScriptsFolder = DatabaseTypeHelper.getScriptsFolderName(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DRIVER_CLASS_NAME));
        String scriptsFolder = MIGRATION_SCRIPTS_DIR + "/" + baseScriptsFolder + "/";
        try {
            final String rootPath = configurationService.getServerRoot();
            File coreJarFile = getMercuriusCoreJarFile(new File(rootPath + "WEB-INF/lib/"));
            if (coreJarFile == null) {
                return null;
            }
            Set<String> scriptNames = new HashSet<String>();
            FileInputStream fin = new FileInputStream(coreJarFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            /** Search scripts in jar file */
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.getName().startsWith(scriptsFolder) && ze.getName().endsWith(".sql")) {
                    scriptNames.add(ze.getName().replace(scriptsFolder, ""));
                }
            }
            return scriptNames;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Get custom scripts
     *
     * @return Set of scripts
     */
    private Set<String> getCustomScripts() {
        String customFolder = configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_FLYWAY_MIGRATION_PARAMETERS.CUSTOM_SCRIPTS_FOLDER);
        if (customFolder == null) {
            return Collections.emptySet();
        }
        final String rootPath = configurationService.getServerRoot();
        File dir = new File(rootPath + "WEB-INF/classes/" + customFolder);
        /** Search module with scrips */
        Set<String> result = new HashSet<String>();
        try {
            for (File file : dir.listFiles()) {
                if (file.getName().startsWith("V") && file.getName().endsWith(".sql")) {
                    result.add(file.getName());
                }
            }
        } catch (NullPointerException e) {
            return null;
        }
        return result;
    }

    /**
     * Get mercurius core jar file
     *
     * @param libDir Libraries directory
     * @return Core jar file
     */
    private File getMercuriusCoreJarFile(File libDir) {
        for (File file : libDir.listFiles()) {
            if (file.getName().startsWith("mercurius-core-")) {
                return file;
            }
        }
        return null;
    }


    /**
     * Set database source
     *
     * @param dataSource Database source
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Set configuration service
     *
     * @param configurationService Configuration service
     */
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Set JDBC template
     *
     * @param jdbcTemplate JDBC template
     */
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
