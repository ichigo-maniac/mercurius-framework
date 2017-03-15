package org.mercuriusframework.controllers;

import org.mercuriusframework.dto.EmployeeEntityDto;
import org.mercuriusframework.enums.WidgetType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.constants.MercuriusMMCConstants;
import org.mercuriusframework.services.MMCApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Node;

/**
 * Mercurius manager console application controller (mercurius-mmc)
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_APPLICATION_PATH)
public class MMCApplicationController {

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
     * Main panel view
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET)
    public String applicationHomePage() {
        if (!userFacade.isCurrentUserEmployee()) {
            return "redirect:" + MercuriusMMCConstants.URLS.BASE_PATH;
        }
        mmcApplicationService.rebuild();
        /** Get necessary widgets */
        EmployeeEntityDto currentEmployee = (EmployeeEntityDto) userFacade.getCurrentUser();
        Node treeView = mmcApplicationService.getWidgetXmlElement(WidgetType.TREE_NODES_VIEW);
        return MercuriusMMCConstants.JSP_TEMPLATES.MAIN_VIEW_JSP;
    }
}
