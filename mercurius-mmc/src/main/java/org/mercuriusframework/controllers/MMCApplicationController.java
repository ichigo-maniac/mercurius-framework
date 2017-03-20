package org.mercuriusframework.controllers;

import org.mercuriusframework.enums.WidgetType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.constants.MercuriusMMCConstants;
import org.mercuriusframework.services.MMCApplicationService;
import org.mercuriusframework.widgets.treenodesview.TreeNodesViewWidget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * @param model View model
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET)
    public String applicationHomePage(Model model) {
        if (!userFacade.isCurrentUserEmployee()) {
            return "redirect:" + MercuriusMMCConstants.URLS.BASE_PATH;
        }
        mmcApplicationService.rebuild();
        /** Get necessary widgets */
        TreeNodesViewWidget treeView = (TreeNodesViewWidget) mmcApplicationService.getWidgetXmlElement(WidgetType.TREE_NODES_VIEW);
        if (treeView != null) {
            model.addAttribute("treeView", treeView);
        }
        return MercuriusMMCConstants.JSP_TEMPLATES.MAIN_VIEW_JSP;
    }
}
