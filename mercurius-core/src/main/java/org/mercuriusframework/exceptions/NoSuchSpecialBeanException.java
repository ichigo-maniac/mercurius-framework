package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * No such special bean exception
 */
public class NoSuchSpecialBeanException extends RuntimeException {

    /**
     * Constructor
     * @param beanName Bean name
     * @param classType Class type
     */
    public NoSuchSpecialBeanException(String beanName, Class classType) {
        super("No " + classType.getName() + " bean with name \"" + StringHelper.escapeHTML(beanName) + "\" has been found");
    }
}
