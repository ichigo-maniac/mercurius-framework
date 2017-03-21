package org.mercuriusframework.helpers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Message source provider
 */
@Service("messageSourceProvider")
public class MessageSourceProvider implements ApplicationContextAware {
    /**
     * Message source
     */
    private static MessageSource messageSource;

    /**
     * Get message
     * @param code Message code
     * @return Message
     */
    public static String getMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());
    }

    /**
     * Get message
     * @param code Message code
     * @param params Message parameters
     * @return Message
     */
    public static String getMessage(String code, Object[] params) {
        return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }

    /**
     * Get message
     * @param code Message code
     * @return Message
     */
    public static String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, new Object[]{}, locale);
    }

    /**
     * Get message
     * @param code Message code
     * @param params Message parameters
     * @return Message
     */
    public static String getMessage(String code, Object[] params, Locale locale) {
        return messageSource.getMessage(code, params, locale);
    }

    /**
     * Set application context
     * @param applicationContext Application context
     * @throws BeansException
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        messageSource = (MessageSource) applicationContext.getBean(MessageSource.class);
    }
}