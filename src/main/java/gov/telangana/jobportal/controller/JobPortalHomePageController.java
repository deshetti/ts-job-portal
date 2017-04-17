package gov.telangana.jobportal.controller;

import gov.telangana.jobportal.repository.Job_notificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@RequestMapping("/portal")
@Controller
public class JobPortalHomePageController {

    private static final String HOME_PAGE = "index";

    @Inject

    private Job_notificationRepository jobNotificationRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String homePage(Model model) {

        model.addAttribute("centralJobCount", jobNotificationRepository.retrieveCentralJobCount());
        model.addAttribute("stateJobCount", jobNotificationRepository.retrieveStateJobCount());
        model.addAttribute("otherJobCount", jobNotificationRepository.retrieveOtherJobCount());
        return HOME_PAGE;
    }

}
