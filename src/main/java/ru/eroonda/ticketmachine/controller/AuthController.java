package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String openNewUserRegistrationPage(Model model) {
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUserHandler(@Valid @ModelAttribute("newUser") User user
            , BindingResult bindingResult) {

        if(userService.findByEmail(user.getEmail())!=null){
            bindingResult.rejectValue("email", "error.email",
                    "User with this email already exist at base. Choose another email");
        }

        if(!user.getPassword().equals(user.getPasswordConfirm())){
            bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm",
                    "This password does not match that entered in the password field, please try again.");

        }
        //TODO: refactor that code by adding DTO, something like that(https://youtu.be/QwQuro7ekvc?t=3435)

        if(bindingResult.hasErrors()){
            return "registration";
        }

        userService.addUser(user);
        return "redirect:registration_success";
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