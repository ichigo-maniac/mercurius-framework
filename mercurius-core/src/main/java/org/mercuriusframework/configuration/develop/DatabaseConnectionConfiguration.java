package org.mercuriusframework.configuration.develop;

import org.apache.commons.dbcp.BasicDataSource;
import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.flyway.DatabaseMigration;
import org.mercuriusframework.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.support.TransactionTemplate;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Database connection configuration (connection pool, entity manager)
 */
@Configuration
@ComponentScan({MercuriusConstants.COMMON.MERCURIUS_BASE_PACKAGE})
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class DatabaseConnectionConfiguration {
    /**
     * Configuration service
     */
    @Autowired
    private ConfigurationService configurationService;

    /**
     * Database data source bean (connection pool)
     * @return Database data source
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource source = new BasicDataSource();
        source.setDriverClassName(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DRIVER_CLASS_NAME));
        source.setUrl(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DATABASE_URL));
        source.setUsername(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DATABASE_USERNAME));
        source.setPassword(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DATABASE_PASSWORD));
        /** Connection pool size configuration */
        return source;
    }

    /**
     * JPA vendor adapter bean
     * @return JPA vendor adapter
     */
    @Bean(name = "jpaVendorAdapter")
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setShowSql(configurationService.getBooleanParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.SHOW_SQL_LOG, true));
        jpaVendorAdapter.setDatabasePlatform(configurationService.getParameter(MercuriusConfigurationParameters.DATABASE_PARAMETERS.DATABASE_HIBERNATE_DIALECT));
        return jpaVendorAdapter;
    }

    /**
     * Entity manager factory bean
     * @param dataSource Data source
     * @param jpaVendorAdapter JPA vendor adapter
     * @return Entity manager factory
     */
    @Bean(name = "entityManagerFactory")
    public AbstractEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(MercuriusConstants.COMMON.MERCURIUS_BASE_PACKAGE_FOR_SCAN);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        return factoryBean;
    }

    /**
     * Transaction manager bean
     * @param entityManagerFactory Entity manager factory
     * @return Transaction manager
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory);
        return transactionManager;
    }

    /**
     * Transaction template bean
     * @param transactionManager Transaction manager
     * @return Transaction template
     */
    @Bean(name = "transactionTemplate")
    public TransactionTemplate transactionTemplate(JpaTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        return transactionTemplate;
    }

    /**
     * JDBC template bean
     * @param dataSource Data source
     * @return JDBC template
     */
    @Bean(name = "jdbcTemplate")
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Flyway database migration bean
     * @param configurationService Configuration service
     * @param dataSource Data source
     * @param jdbcTemplate JDBC template
     * @return Flyway migration service
     */
    @Bean(name = "databaseMigration")
    public DatabaseMigration databaseMigration(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate, ConfigurationService configurationService) {
        DatabaseMigration databaseMigration = new DatabaseMigration();
        databaseMigration.setDataSource(dataSource);
        databaseMigration.setJdbcTemplate(jdbcTemplate);
        databaseMigration.setConfigurationService(configurationService);
        return databaseMigration;
    }

}
