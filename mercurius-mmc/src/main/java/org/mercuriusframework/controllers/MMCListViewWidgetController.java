package org.mercuriusframework.controllers;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.constants.MercuriusMMCConstants;
import org.mercuriusframework.constants.MercuriusMMCWidgetsConstants;
import org.mercuriusframework.controllers.response.CharArrayWriterResponse;
import org.mercuriusframework.dto.LoadWidgetResult;
import org.mercuriusframework.entities.ProductEntity;
import org.mercuriusframework.enums.LoadWidgetResultStatus;
import org.mercuriusframework.enums.WidgetType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.services.AnnotationService;
import org.mercuriusframework.services.EntityService;
import org.mercuriusframework.services.MMCApplicationService;
import org.mercuriusframework.services.query.PageableResult;
import org.mercuriusframework.widgets.listview.ListViewWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Node;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Mercurius manager console list view widget controller (mercurius-mmc)
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_WIDGETS_PATH)
public class MMCListViewWidgetController {

    /**
     * Constants
     */
    private static final String LIST_VIEW_TEMPLATE = "/WEB-INF/view/mmc/templates/list_view_template.jsp";

    /**
     * User facade
     */
    @Autowired
    @Qualifier("userFacade")
    private UserFacade userFacade;

    /**
     * MMC application service
     */
    @Autowired
    @Qualifier("mmcApplicationService")
    private MMCApplicationService mmcApplicationService;

    /**
     * Entity service
     */
    @Autowired
    @Qualifier("entityService")
    private EntityService entityService;

    /**
     * Annotation service
     */
    @Autowired
    @Qualifier("annotationService")
    private AnnotationService annotationService;

    /**
     * Load widget
     * @param entityName Entity name
     * @param page Page
     * @param request Http-request
     * @param response Http-response
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = MercuriusMMCWidgetsConstants.ListView.WIDGET_NAME + "/{entityName}")
    @ResponseBody
    public LoadWidgetResult loadWidget(@PathVariable String entityName, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                       HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!userFacade.isCurrentUserEmployee()) {
            return new LoadWidgetResult(LoadWidgetResultStatus.ERROR);
        }
        Node xmlElement = mmcApplicationService.getEntityWidgetXmlElement(WidgetType.LIST_VIEW, entityName);
        if (xmlElement == null) {
            return new LoadWidgetResult(LoadWidgetResultStatus.NOT_FOUND);
        }
        /** Load data */
        Class entityClass = annotationService.getEntityClassByEntityName(entityName);
        if (entityClass == null) {
            return new LoadWidgetResult(LoadWidgetResultStatus.NOT_FOUND);
        }
        PageableResult<ProductEntity> loadedData = entityService.getPageableResultByCriteria(page, 2, entityClass);
        /** Transform widget */
        ListViewWidget listViewWidget = new ListViewWidget(xmlElement);
        String rendererResult = renderListViewFragment(request, response, entityName, listViewWidget, loadedData);
        if (StringUtils.isEmpty(rendererResult)) {
            return new LoadWidgetResult(LoadWidgetResultStatus.ERROR);
        } else {
            return new LoadWidgetResult(rendererResult, LoadWidgetResultStatus.CORRECT);
        }
    }


    /**
     * Render list view widget
     * @param request Http-request
     * @param response Http-response
     * @param entityName Entity name
     * @param listViewWidget List view widget
     * @param dataResult Loaded data
     * @return Rendered html-fragment
     * @throws ServletException
     * @throws IOException
     */
    private String renderListViewFragment(HttpServletRequest request, HttpServletResponse response,
                                          String entityName, ListViewWidget listViewWidget, PageableResult dataResult) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(LIST_VIEW_TEMPLATE);
        CharArrayWriterResponse customResponse  = new CharArrayWriterResponse(response);
        request.setAttribute("entityName", entityName);
        request.setAttribute("listView", listViewWidget);
        request.setAttribute("dataResult", dataResult);
        requestDispatcher.forward(request, customResponse);
        return customResponse.getOutput();
    }
}
