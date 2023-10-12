package com.example.demo.Controller;

import com.example.demo.Model.Applicant;
import com.example.demo.Model.Employer;
import com.example.demo.Service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EmployerController {
    @Autowired
    private final EmployerService employerService;
    @PostMapping("/register/employer")
    public String createEmployer(@ModelAttribute("employer") Employer employer) {
        employerService.registerEmployer(employer);
        return  "redirect:/login/employer";
    }
    @GetMapping("/login/employer")
    public String loginEmployer(Model model) {
        model.addAttribute("employer", new Employer());
        return  "login.html";
    }
    @GetMapping("/register/employer")
    public String showRegisterForm(Model model) {
        model.addAttribute("employer", new Employer());
        return "register.html";
    }
}
