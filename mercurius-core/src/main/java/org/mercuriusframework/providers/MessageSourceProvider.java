package org.mercuriusframework.providers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
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
        try {
            return messageSource.getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "[" + code + "]";
        }
    }

    /**
     * Get message
     * @param code Message code
     * @param params Message parameters
     * @return Message
     */
    public static String getMessage(String code, Object[] params) {
        try {
            return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return "[" + code + "]";
        }
    }

    /**
     * Get message
     * @param code Message code
     * @return Message
     */
    public static String getMessage(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, new Object[]{}, locale);
        } catch (NoSuchMessageException e) {
            return "[" + code + "]";
        }
    }

    /**
     * Get message
     * @param code Message code
     * @param params Message parameters
     * @return Message
     */
    public static String getMessage(String code, Object[] params, Locale locale) {
        try {
            return messageSource.getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
            return "[" + code + "]";
        }
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