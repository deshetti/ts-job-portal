package gov.telangana.jobportal.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.telangana.jobportal.repository.Job_notificationRepository;

/**
 * Created by deshetti on 4/16/17.
 */

@Controller
public class JobPortalJobDetailsController {

    private static final String JOB_DETAILS = "job_details";
    private static final String JOB_DETAILS_VIEW = "jobDetailsView";

    @Inject
    private Job_notificationRepository jobNotificationRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/jobDetails/{jobId}")
    public String jobsDetails(Model model, @PathVariable String jobId) {
        model.addAttribute(JOB_DETAILS_VIEW, jobNotificationRepository.findOne(Long.parseLong(jobId)));
        return JOB_DETAILS;
    }

}
