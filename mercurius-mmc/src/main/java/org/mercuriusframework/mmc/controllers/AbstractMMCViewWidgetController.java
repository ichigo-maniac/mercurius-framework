package org.mercuriusframework.mmc.controllers;

import com.Ostermiller.util.StringHelper;
import org.mercuriusframework.mmc.dto.LoadWidgetResult;
import org.mercuriusframework.mmc.enums.LoadWidgetResultStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Abstract mmc-view widget controller
 */
@Controller
public abstract class AbstractMMCViewWidgetController {

    /**
     * Constants
     */
    protected static final String USER_IS_NOT_EMPLOYEE_MESSAGE = "mmc.user.is.not.employee";
    protected static final String WIDGET_HAS_NOT_BEEN_FOUND_MESSAGE = "mmc.widget.has.not.been.found";
    protected static final String WIDGET_RENDER_ERROR_MESSAGE = "mmc.widget.render.error";

    /**
     * Load error handler
     * @param exception Exception
     * @return Load widget result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public LoadWidgetResult loadErrorHandler(Exception exception) {
        return new LoadWidgetResult(LoadWidgetResultStatus.ERROR,
                StringHelper.escapeHTML(exception.getMessage()) + "<br>" +
                        StringHelper.escapeHTML(exception.getCause().getMessage()));
    }
}
