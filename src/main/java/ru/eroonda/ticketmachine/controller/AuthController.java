package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.dto.UserDto;
import ru.eroonda.ticketmachine.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String openNewUserRegistrationPage(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUserHandler(@Valid @ModelAttribute("newUser") UserDto userFromRequest
            , BindingResult bindingResult) {

        return userService.addUser(userFromRequest,bindingResult );
    }

    @RequestMapping("/login")
    public String openLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/restore")
    public String openAccountRestorePage() {

        return "restore";
    }

    @GetMapping("/registration_success")
    public String registrationSuccessPage() {
        return "registration_success";
    }

}