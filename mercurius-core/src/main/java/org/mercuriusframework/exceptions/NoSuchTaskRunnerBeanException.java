package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * No such task runner bean exception
 */
public class NoSuchTaskRunnerBeanException extends RuntimeException {
    /**
     * Constructor
     * @param beanName Bean name
     */
    public NoSuchTaskRunnerBeanException(String beanName) {
        super("No TaskRunner(extends AbstractTaskRunner) with name " + StringHelper.escapeHTML(beanName) + " bean has been found");
    }
}
