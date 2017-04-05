package org.mercuriusframework.configuration.develop;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.mercuriusframework.constants.MercuriusConfigurationParameters;
import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Solr connection configuration
 */
@Configuration
@EnableSolrRepositories
@Profile(MercuriusConstants.PROFILES.SOLR_SEARCH_PROFILES)
public class SolrConnectionConfiguration {

    /**
     * Configuration service
     */
    @Autowired
    private ConfigurationService configurationService;

    /**
     * Solr client bean
     * @return Solr client
     */
    @Bean(name = "solrClient")
    public SolrClient solrClient() throws IOException, SAXException, ParserConfigurationException {
        String httpSolrPath = configurationService.getParameter(MercuriusConfigurationParameters.SOLR_PARAMETERS.SOLR_HTTP_PATH);
        return new HttpSolrClient(httpSolrPath);
    }

    /**
     * Solr template bean
     * @param solrClient Solr client
     * @return Solr template
     */
    @Bean(name = "solrTemplate")
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }
}
