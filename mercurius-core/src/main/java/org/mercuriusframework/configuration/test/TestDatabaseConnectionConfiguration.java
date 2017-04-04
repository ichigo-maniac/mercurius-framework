package org.mercuriusframework.configuration.test;

import org.mercuriusframework.constants.MercuriusConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Database connection configuration for testing (embedded data source, entity manager)
 */
@Configuration
@ComponentScan({MercuriusConstants.COMMON.MERCURIUS_BASE_PACKAGE})
@Profile(MercuriusConstants.PROFILES.TEST_PROFILE)
public class TestDatabaseConnectionConfiguration {
    /**
     * Constants
     */
    private static final String H2_DIALECT = "org.hibernate.dialect.H2Dialect";

    /**
     * Database data source bean (embedded datasource)
     * @return Database data source
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase dataSource = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScripts(
                        "test_sql_migration/V1_01_20160314_1200__catalogs.sql",
                        "test_sql_migration/V1_01_20160328_1000__units.sql",
                        "test_sql_migration/V1_01_20160328_1100__categories.sql",
                        "test_sql_migration/V1_01_20160328_1200__products.sql",
                        "test_sql_migration/V1_01_20160329_1100__stores.sql",
                        "test_sql_migration/V1_01_20160329_1200__prices.sql",
                        "test_sql_migration/V1_01_20160329_1300__user.sql",
                        "test_sql_migration/V1_01_20160329_1400__features.sql",
                        "test_sql_migration/V1_01_20160329_1500__solr.sql"
                )
                .build();
        return dataSource;
    }

    /**
     * JPA vendor adapter bean
     * @return JPA vendor adapter
     */
    @Bean(name = "jpaVendorAdapter")
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabasePlatform(H2_DIALECT);
        return jpaVendorAdapter;
    }

    /**
     * Entity manager factory bean
     * @param dataSource Data source
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
     *
     */
    @Bean(name = "jdbcTemplate")
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
