package ru.eroonda.ticketmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.entity.User;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/registration")
    public String openNewUserRegistrationPage(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUserHandler(@ModelAttribute("newUser") User user){
        System.out.println(user);
        //TODO:user.isEnabled = true
        return "redirect:registration_success";
    }

    @RequestMapping("/login")
    public String openLoginPage(Model model){
        return "login";
    }

    @GetMapping("/restore")
    public String openAccountRestorePage(){

        return "restore";
    }

    @GetMapping("/registration_success")
    public String registrationSuccessPage(){
        return "registration_success";
    }

    @GetMapping("test_page")
    public String testP(){
        return "test_page";
    }

}
