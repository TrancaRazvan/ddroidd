package com.example.demo.Controller;

import com.example.demo.Model.Job;
import com.example.demo.Service.UserService;
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
    private final UserService userService;
    @Autowired
    private final JobService jobService;
    @GetMapping("/")
    public String showIndexPage(Model model, Authentication authentication) {

        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);

        return "index.html";
    }
    @GetMapping("/home")
    public String showHomePageForUsers(Model model, Authentication authentication) {

        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);

        return "home.html";
    }


}
