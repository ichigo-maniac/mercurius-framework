package org.mercuriusframework.mmc.dto;

import org.mercuriusframework.mmc.enums.LoadWidgetResultStatus;
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
     * Error message
     */
    private String errorMessage;

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
     * Constructor
     * @param status Load status
     * @param errorMessage Error message
     */
    public LoadWidgetResult(LoadWidgetResultStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
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

    /**
     * Get error message
     * @return Error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message
     * @param errorMessage Error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
