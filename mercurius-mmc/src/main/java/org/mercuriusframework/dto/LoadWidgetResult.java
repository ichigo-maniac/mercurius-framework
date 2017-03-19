package org.mercuriusframework.dto;

import org.mercuriusframework.enums.LoadWidgetResultStatus;
import java.io.Serializable;

/**
 * Load widget result data transfer object
 */
public class LoadWidgetResult implements Serializable {

    /**
     * Rendered widget (html-fragment)
     */
    private String renderedWidget;

    /**
     * Load status
     */
    private LoadWidgetResultStatus status;

    /**
     * Constructor
     * @param renderedWidget Rendered widget
     * @param status Load status
     */
    public LoadWidgetResult(String renderedWidget, LoadWidgetResultStatus status) {
        this.renderedWidget = renderedWidget;
        this.status = status;
    }

    /**
     * Constructor
     * @param status Load status
     */
    public LoadWidgetResult(LoadWidgetResultStatus status) {
        this.status = status;
        this.renderedWidget = "";
    }

    /**
     * Get rendered widget (html-fragment)
     * @return Rendered widget (html-fragment)
     */
    public String getRenderedWidget() {
        return renderedWidget;
    }

    /**
     * Set rendered widget (html-fragment)
     * @param renderedWidget Rendered widget (html-fragment)
     */
    public void setRenderedWidget(String renderedWidget) {
        this.renderedWidget = renderedWidget;
    }

    /**
     * Get load status
     * @return Load status
     */
    public LoadWidgetResultStatus getStatus() {
        return status;
    }

    /**
     * Set load status
     * @param status Load status
     */
    public void setStatus(LoadWidgetResultStatus status) {
        this.status = status;
    }
}
