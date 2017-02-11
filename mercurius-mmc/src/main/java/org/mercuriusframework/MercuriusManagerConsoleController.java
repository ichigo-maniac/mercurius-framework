package org.mercuriusframework;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Mercurius manager console controller (mercurius-mmc)
 */
@Controller
public class MercuriusManagerConsoleController {
    @RequestMapping(method = RequestMethod.GET, value = "/mercurius/mmc")
    public String mainPageView() {
        return "mmc/index";
    }
}
