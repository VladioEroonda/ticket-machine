package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.service.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String openNewUserRegistrationPage(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUserHandler(@ModelAttribute("newUser") User user){
        userService.addUser(user);
        //TODO:проверка на существующего пользователя
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

}
