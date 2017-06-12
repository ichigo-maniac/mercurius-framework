package org.mercuriusframework.configuration.develop;

import org.mercuriusframework.constants.MercuriusConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.IOException;

/**
 * Multipart resolver configuration
 */
@Configuration
@Profile(MercuriusConstants.PROFILES.DEVELOP_PROFILE)
public class MultipartResolverConfiguration {

    /**
     * Multipart resolver bean
     * @return Multipart resolver
     * @throws IOException
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        return new StandardServletMultipartResolver();
    }

    /**
     * Rest template
     * @return Rest template
     */
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
