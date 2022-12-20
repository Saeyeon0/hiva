package com.example.demosecurity.controllers;

import com.example.demosecurity.models.User;
import com.example.demosecurity.service.EmailService;
import com.example.demosecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;
    @Value("${spring.mail.username}")
    private String email;
    @GetMapping("/user-type")
    public ModelAndView getLogin() {
        return new ModelAndView("index");
    }
    @GetMapping("/admin")
    public String admin(){
        return "home";
    }
    @GetMapping("/test-mail")
    @ResponseBody
    public String sendTestMail(@RequestParam(name = "mail",required = false)String to){
        return emailService.sendTestMailTo((to == null || to.isEmpty()) ? email : to);
    }

    @GetMapping("/registration")
    public ModelAndView registration(Model model) {
        model.addAttribute("user", new User());
        return new ModelAndView("authorization");
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute("user") User userForm, BindingResult bindingResult) {

        userService.createUser(userForm);

        return new ModelAndView("redirect:/registration?success");

    }

    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @GetMapping("/login")
    public ModelAndView registration(){
        return new ModelAndView("home");
    }
}
