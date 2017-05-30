package org.mercuriusframework.dataimport.controllers;

import org.apache.commons.lang.StringUtils;
import org.mercuriusframework.dataimport.constants.MercuriusDataImportConstants;
import org.mercuriusframework.dataimport.services.DataImportService;
import org.mercuriusframework.dataimport.services.PackageDataImportService;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.providers.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
        String redirectString = getRedirectInErrors();
        if (redirectString != null) {
            return redirectString;
        }
        return MercuriusDataImportConstants.JSP_TEMPLATES.MAIN_VIEW_JSP;
    }

    /**
     * Import string data
     * @param rawText Raw text value
     * @param model Model
     * @return View path
     */
    @RequestMapping(method = RequestMethod.POST, value = MercuriusDataImportConstants.URLS.IMPORT_STRING_DATA)
    public String importData(@RequestParam String rawText, RedirectAttributes model) {
        String redirectString = getRedirectInErrors();
        if (redirectString != null) {
            return redirectString;
        }
        String errorLog = dataImportService.importData(rawText.trim());
        if (StringUtils.isNotEmpty(errorLog)) {
            model.addFlashAttribute("errorLog", errorLog);
        }
        model.addFlashAttribute("importFinish", true);
        return "redirect:" + MercuriusDataImportConstants.URLS.BASE_APPLICATION_PATH;
    }

    /**
     * Files import data view
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET, value = MercuriusDataImportConstants.URLS.IMPORT_FILES_DATA)
    public String importFilesDataView() {
        String redirectString = getRedirectInErrors();
        if (redirectString != null) {
            return redirectString;
        }
        return MercuriusDataImportConstants.JSP_TEMPLATES.FILES_IMPORT;
    }


    /**
     * Import files data
     * @param importFiles Import files array
     * @param model Model
     * @return View result
     */
    @RequestMapping(method = RequestMethod.POST, value = MercuriusDataImportConstants.URLS.IMPORT_FILES_DATA)
    public String importFilesData( @RequestParam MultipartFile[] importFiles, RedirectAttributes model)  {
        String redirectString = getRedirectInErrors();
        if (redirectString != null) {
            return redirectString;
        }
        if (importFiles != null && importFiles.length > 0) {
            StringBuilder logResult = new StringBuilder();
            for (MultipartFile file : importFiles) {
                logResult.append(dataImportService.importData(file));
            }
            if (!StringUtils.isEmpty(logResult.toString().trim())) {
                model.addFlashAttribute("errorLog", logResult.toString().trim());
            }
        }
        model.addFlashAttribute("importFinish", true);
        return "redirect:" + MercuriusDataImportConstants.URLS.BASE_APPLICATION_PATH + MercuriusDataImportConstants.URLS.IMPORT_FILES_DATA;
    }

    /**
     * Import package data view
     * @param model Model
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET, value = MercuriusDataImportConstants.URLS.IMPORT_PACKAGE_DATA)
    public String importPackageDataView(Model model) {
        String redirectString = getRedirectInErrors();
        if (redirectString != null) {
            return redirectString;
        }
        model.addAttribute("packageImportServices", ApplicationContextProvider.getBeansNames(PackageDataImportService.class));
        return MercuriusDataImportConstants.JSP_TEMPLATES.PACKAGE_IMPORT;
    }

    /**
     * Import package data
     * @return View path
     */
    @RequestMapping(method = RequestMethod.POST, value = MercuriusDataImportConstants.URLS.IMPORT_PACKAGE_DATA)
    public String importPackageData(@RequestParam(value = "packageServices", required = false) String[] servicesNames, RedirectAttributes model) {
        String redirectString = getRedirectInErrors();
        if (redirectString != null) {
            return redirectString;
        }
        /** Check selected services */
        if (servicesNames != null) {
            StringBuilder errorLog = new StringBuilder();
            for (String serviceName : servicesNames) {
                PackageDataImportService importService = ApplicationContextProvider.getBean(serviceName, PackageDataImportService.class);
                String errors = importService.importData();
                if (StringUtils.isNotEmpty(errors)) {
                    errorLog.append(errors + "<br>");
                }
            }
            if (StringUtils.isNotEmpty(errorLog.toString().trim())) {
                model.addFlashAttribute("errorLog", errorLog);
            }
        }
        model.addFlashAttribute("importFinish", true);
        return "redirect:" + MercuriusDataImportConstants.URLS.BASE_APPLICATION_PATH + MercuriusDataImportConstants.URLS.IMPORT_PACKAGE_DATA;
    }

    /**
     * Get redirect in error cases
     * @return Redirect string or null if everything is ok
     */
    private String getRedirectInErrors() {
        if (!userFacade.isCurrentUserEmployee()) {
            return "redirect:" + MercuriusDataImportConstants.URLS.BASE_PATH;
        }
        if (!userFacade.hasCurrentUserRole(MercuriusDataImportConstants.COMMON.DATA_IMPORT_ROLE_CODE)) {
            return "redirect:" + MercuriusDataImportConstants.URLS.HOME_PATH;
        }
        return null;
    }
}
