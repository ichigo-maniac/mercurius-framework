package org.mercuriusframework.exceptions;

import com.Ostermiller.util.StringHelper;

/**
 * No such task runner bean exception
 */
public class NoSuchSolrIndexTaskRunnerBeanException extends RuntimeException {
    /**
     * Constructor
     * @param beanName Bean name
     */
    public NoSuchSolrIndexTaskRunnerBeanException(String beanName) {
        super("No SolrIndexTaskRunner(extends SolrIndexTaskRunner) with name " + StringHelper.escapeHTML(beanName) + " bean has been found");
    }
}