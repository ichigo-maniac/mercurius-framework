package org.mercuriusframework.mmc.controllers;

import org.mercuriusframework.facades.TaskFacade;
import org.mercuriusframework.facades.UserFacade;
import org.mercuriusframework.mmc.constants.MercuriusMMCConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Mercurius manager console task run controller
 */
@Controller
@RequestMapping(MercuriusMMCConstants.URLS.BASE_PATH)
public class MMCPanelTaskRunController {

    /**
     * User facade
     */
    @Autowired
    @Qualifier("userFacade")
    private UserFacade userFacade;

    /**
     * Task facade
     */
    @Autowired
    @Qualifier("taskFacade")
    protected TaskFacade taskFacade;

    /**
     * Run task
     * @param taskCode Task code
     * @return View path
     */
    @RequestMapping(method = RequestMethod.POST, value = "/run_task/{taskCode}")
    @ResponseBody
    public Object runTask(@PathVariable String taskCode) {
        if (userFacade.hasCurrentUserRole(MercuriusMMCConstants.COMMON.ADMIN_ROLE_CODE)) {
            taskFacade.runTask(taskCode);
        }
        return null;
    }
}
