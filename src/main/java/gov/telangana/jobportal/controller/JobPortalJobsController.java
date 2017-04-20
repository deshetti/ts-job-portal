package gov.telangana.jobportal.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.telangana.jobportal.domain.Job_notification;
import gov.telangana.jobportal.repository.Job_notificationRepository;

@Controller
public class JobPortalJobsController {

    private static final String CENTRAL_GOVT_JOBS = "central_govt_jobs";
    private static final String STATE_GOVT_JOBS = "state_govt_jobs";
    private static final String OTHER_STATE_GOVT_JOBS = "other_state_govt_jobs";

    @Inject
    private Job_notificationRepository jobNotificationRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/central-govt-jobs")
    public String centralJobs(Model model, Pageable pageable) {
        Page<Job_notification> jobNotificationPage = jobNotificationRepository.findByJobType_Type("Central Government Job", pageable);
        model.addAttribute("centralGovtJobs", jobNotificationPage.getContent());
        return CENTRAL_GOVT_JOBS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/state-govt-jobs")
    public String stateJobs(Model model, Pageable pageable) {
        Page<Job_notification> jobNotificationPage = jobNotificationRepository.findByJobType_Type("State Government Job", pageable);
        model.addAttribute("stateGovtJobs", jobNotificationPage.getContent());
        return STATE_GOVT_JOBS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/other-state-govt-jobs")
    public String otherStateJobs(Model model, Pageable pageable) {
        Page<Job_notification> jobNotificationPage = jobNotificationRepository.findByJobType_Type("Other", pageable);
        model.addAttribute("otherGovtJobs", jobNotificationPage.getContent());
        return OTHER_STATE_GOVT_JOBS;
    }

}
