package org.mercuriusframework.dataimport.controllers;

import org.mercuriusframework.dataimport.constants.MercuriusDataImportConstants;
import org.mercuriusframework.dataimport.services.DataImportService;
import org.mercuriusframework.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * Data import service
     */
    @Autowired
    @Qualifier("dataImportService")
    private DataImportService dataImportService;

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

    /**
     * Import string data
     * @param rawText Raw text value
     * @param model Model
     * @return View path
     */
    @RequestMapping(method = RequestMethod.POST, value = "/import_string_data")
    public String importData(@RequestParam String rawText, Model model) {
        if (!userFacade.isCurrentUserEmployee()) {
            return "redirect:" + MercuriusDataImportConstants.URLS.BASE_PATH;
        }
        String importResult = dataImportService.importData(rawText.trim());
        return "redirect:" + MercuriusDataImportConstants.URLS.BASE_APPLICATION_PATH;

    }
}
