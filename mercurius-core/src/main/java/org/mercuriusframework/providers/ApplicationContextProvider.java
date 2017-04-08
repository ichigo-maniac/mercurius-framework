package org.mercuriusframework.providers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by impi on 29.02.16.
 */
@Service("applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {
    /**
     * Application context
     */
    private static ApplicationContext context;

    /**
     * Get spring application context
     * @return Application context
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * Get spring bean
     * @param beanName Bean name
     * @param beanClass Bean class
     * @param <T> Type
     * @return Spring bean
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        return context.getBean(beanName, beanClass);
    }

    /**
     * Get spring bean
     * @param beanName Bean name
     * @return Spring bean
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * Set spring application context
     * @param applicationContext Application context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
