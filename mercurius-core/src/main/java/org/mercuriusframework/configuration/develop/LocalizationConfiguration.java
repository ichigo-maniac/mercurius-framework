package org.mercuriusframework.configuration.develop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:localization/mercurius-core-labels",
                "classpath:localization/mercurius-mmc-labels"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}

