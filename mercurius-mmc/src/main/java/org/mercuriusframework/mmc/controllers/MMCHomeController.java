package org.mercuriusframework.mmc.controllers;

import org.mercuriusframework.mmc.constants.MercuriusMMCConstants;
import org.mercuriusframework.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;

/**
 * Mercurius manager console home controller (mercurius-mmc)
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_PATH)
public class MMCHomeController {

    /**
     * User facade
     */
    @Autowired
    private UserFacade userFacade;

    /**
     * Home page
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET)
    public String mainPageView() {
        if (userFacade.isCurrentUserEmployee()) {
            return "redirect:" + MercuriusMMCConstants.URLS.BASE_APPLICATION_PATH;
        }
        return MercuriusMMCConstants.JSP_TEMPLATES.HOME_JSP;
    }

    /**
     * Login employee
     * @param username Username
     * @param password Password
     * @return View path
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String logIn(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        boolean loginResult = userFacade.logInEmployee(username, password);
        if (loginResult) {
            return "redirect:" + MercuriusMMCConstants.URLS.BASE_APPLICATION_PATH;
        } else {
            redirectAttributes.addAttribute("errorLogin", true);
            return "redirect:" + MercuriusMMCConstants.URLS.BASE_PATH;
        }
    }

    /**
     * Logout employee
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logOut() throws ServletException {
        userFacade.logOutCurrentUser();
        return "redirect:" + MercuriusMMCConstants.URLS.BASE_PATH;
    }
}
