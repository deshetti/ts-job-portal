package gov.telangana.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JobPortalJobsController {

    private static final String CENTRAL_GOVT_JOBS = "central_govt_jobs";
    private static final String STATE_GOVT_JOBS = "state_govt_jobs";
    private static final String OTHER_STATE_GOVT_JOBS = "other_state_govt_jobs";

    @RequestMapping(method = RequestMethod.GET, path = "/central-govt-jobs")
    public String centralJobs(Model model) {
        return CENTRAL_GOVT_JOBS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/state-govt-jobs")
    public String stateJobs(Model model) {
        return STATE_GOVT_JOBS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/other-state-govt-jobs")
    public String otherStateJobs(Model model) {
        return OTHER_STATE_GOVT_JOBS;
    }

}
