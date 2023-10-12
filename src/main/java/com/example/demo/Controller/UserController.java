package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") User user) {
        userService.registerEmployer(user);
        return  "redirect:/login";
    }
    @GetMapping("/login")
    public String loginUser(Model model) {
        model.addAttribute("user", new User());
        return  "login-user.html";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register-user.html";
    }
}
