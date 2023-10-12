package com.example.demo.Controller;

import com.example.demo.Model.Job;
import com.example.demo.Service.EmployerService;
import com.example.demo.Service.JobService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @Autowired
    private final EmployerService employerService;
    @Autowired
    private final JobService jobService;
    @GetMapping("/login/account")
    public String showLoginForm(Model model) {
        return "login-choose.html";
    }

    @GetMapping("/register/account")
    public String showRegisterForm(Model model) {
        return "register-choose.html";
    }
    @GetMapping("/")
    public String showIndexPage(Model model, Authentication authentication) {

        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);

        return "index.html";
    }
    @GetMapping("/home/user")
    public String showHomePageForUsers(Model model, Authentication authentication) {

        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);

        return "index-user.html";
    }
    @GetMapping("/home/employer")
    public String showHomePageForEmployer(Model model, Authentication authentication) {

        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);

        return "index-employer.html";
    }

}
