package org.mercuriusframework.controllers;

import org.mercuriusframework.constants.MercuriusDataImportConstants;
import org.mercuriusframework.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Data import application controller
 */
@Controller
@RequestMapping(MercuriusDataImportConstants.URLS.BASE_APPLICATION_PATH)
public class DataImportApplicationController {

    /**
     * User facade
     */
    @Autowired
    @Qualifier("userFacade")
    private UserFacade userFacade;

    /**
     * Main panel view
     * @param model View model
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET)
    public String applicationHomePage(Model model) {
        if (!userFacade.isCurrentUserEmployee()) {
            return "redirect:" + MercuriusDataImportConstants.URLS.BASE_PATH;
        }
        return MercuriusDataImportConstants.JSP_TEMPLATES.MAIN_VIEW_JSP;
    }
}
