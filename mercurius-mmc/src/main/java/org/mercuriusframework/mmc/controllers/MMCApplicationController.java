package org.mercuriusframework.mmc.controllers;

import org.mercuriusframework.mmc.enums.WidgetType;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.mmc.constants.MercuriusMMCConstants;
import org.mercuriusframework.mmc.services.MMCApplicationService;
import org.mercuriusframework.mmc.widgets.treenodesview.TreeNodesViewWidget;
import org.mercuriusframework.services.SolrIndexTaskService;
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
     * Solr index task service
     */
    @Autowired
    @Qualifier("solrIndexTaskService")
    private SolrIndexTaskService solrIndexTaskService;

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
        /** Solr tasks */
        if (userFacade.hasCurrentUserRole(MercuriusMMCConstants.COMMON.ADMIN_ROLE_CODE)) {
            model.addAttribute("solrTasks", solrIndexTaskService.getAllTasks());
        }
        return MercuriusMMCConstants.JSP_TEMPLATES.MAIN_VIEW_JSP;
    }
}
