package gov.telangana.jobportal.controller;

import gov.telangana.jobportal.domain.Job_notification;
import gov.telangana.jobportal.repository.Job_notificationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by deshetti on 4/20/17.
 */

@Controller
public class JobPortalIconsPageController {

    private static final String CENTRAL_GOVT_JOBS_ICONS = "central-govt-jobs-icons";
    private static final String STATE_GOVT_JOBS_ICONS = "state-govt-jobs-icons";
    private static final String OTHER_STATE_GOVT_JOBS_ICONS = "other-state-govt-jobs-icons";

    @RequestMapping(method = RequestMethod.GET, path = "/central-govt-jobs-icons")
    public String centralGovtJobsIcons(Model model) {
        return CENTRAL_GOVT_JOBS_ICONS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/state-govt-jobs-icons")
    public String stateGovtJobsIcons(Model model) {
        return STATE_GOVT_JOBS_ICONS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/other-state-govt-jobs-icons")
    public String otherStateGovtJobsIcons(Model model) {
        return OTHER_STATE_GOVT_JOBS_ICONS;
    }

}
