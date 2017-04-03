package org.mercuriusframework.configuration.develop;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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

}
