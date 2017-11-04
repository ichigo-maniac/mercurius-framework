package org.mercuriusframework.configuration.common;

import org.mercuriusframework.constants.MercuriusConstants;
import org.mercuriusframework.facades.impl.WildcardReloadableResourceBundleMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Localization configuration
 */
@Configuration
public class LocalizationConfiguration {

    /**
     * Message source bean
     * @return Message source
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        WildcardReloadableResourceBundleMessageSource messageSource = new WildcardReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

