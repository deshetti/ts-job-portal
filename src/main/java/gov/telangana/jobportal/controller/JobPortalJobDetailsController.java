package gov.telangana.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by deshetti on 4/16/17.
 */

@Controller
public class JobPortalJobDetailsController {

    private static final String JOB_DETAILS = "job_details";

    @RequestMapping(method = RequestMethod.GET, path = "/jobDetails")
    public String jobsDetails(Model model) {
        return JOB_DETAILS;
    }

}
