package com.example.demo.Controller;

import com.example.demo.Model.Applicant;
import com.example.demo.Model.Job;
import com.example.demo.Model.User;
import com.example.demo.Repository.JobRepository;
import com.example.demo.Service.JobService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class JobController {
    @Autowired
    private final JobService jobService;
    @Autowired
    private final JobRepository jobRepository;
    @Autowired
    private final UserService userService;
    private final Logger logger = Logger.getLogger(UserService.class.getName());


    @GetMapping("/create/job")
    public String loginEmployer(Model model) {
        model.addAttribute("job", new Job());
        return "create-job.html";
    }

    @GetMapping("/job/fail/create")
    public String failCreate() {
        return "job-fail-create.html";
    }

//    @PostMapping("/create/job")
//    public String createJob(@ModelAttribute("job") Job job, Authentication authentication) {
//        String username = authentication.getName();
//        User user = userService.findByUsername(username);
//        if (user != null && user.getRole().equalsIgnoreCase("EMPLOYER")) {
//            jobService.addEmpToJob(job, user.getId());
//            return "redirect:/home";
//        } else {
//            return "redirect:/job/fail/create";
//        }
//    }

    @GetMapping("/apply/{jobId}")
    public String showApplyForm(@PathVariable Long jobId, Model model) {
        model.addAttribute("jobId", jobId);
        model.addAttribute("applicant", new Applicant());
        return "apply-form.html";
    }

    @PostMapping("/apply/{jobId}")
    public String submitApplication(@PathVariable Long jobId, @ModelAttribute("applicant") Applicant applicant) {
        System.out.println("jobId: " + jobId);

        boolean added = jobService.addAplicantToJob(jobId, applicant);
        if (added) {
            return "redirect:/success";
        } else {
            return "redirect:/job/fail/apply";
        }
    }
    @GetMapping("/success")
    public String showSucces(){
        return "success.html";
    }

    @GetMapping("/job/fail/apply")
    public String failApply() {
        return "job-fail-apply.html";
    }
}
