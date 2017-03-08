package org.mercuriusframework;

import org.mercuriusframework.constants.MercuriusMMCConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Mercurius manager console home controller (mercurius-mmc)
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_PATH)
public class MMCHomeController {

    /**
     * Home page
     * @return View path
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String mainPageView() {
        return MercuriusMMCConstants.JSP_TEMPLATES.HOME_JSP;
    }
}
