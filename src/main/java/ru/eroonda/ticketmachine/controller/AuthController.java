package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.dto.EmailValidatorDto;
import ru.eroonda.ticketmachine.dto.PasswordResetDto;
import ru.eroonda.ticketmachine.dto.UserDto;
import ru.eroonda.ticketmachine.service.RegistrationConfirmationTokenService;
import ru.eroonda.ticketmachine.service.ResetPasswordTokenConfirmationService;
import ru.eroonda.ticketmachine.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    RegistrationConfirmationTokenService registrationConfirmationTokenService;
    @Autowired
    ResetPasswordTokenConfirmationService resetPasswordTokenConfirmationService;

    @GetMapping("/registration")
    public String openNewUserRegistrationPage(Model model) {
        model.addAttribute("newUser", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUserHandler(@Valid @ModelAttribute("newUser") UserDto userFromRequest,
                                             BindingResult bindingResult) {

        return userService.addUser(userFromRequest, bindingResult);
    }

    @RequestMapping("/login")
    public String openLoginPage(Model model) {
        return "login";
    }


    @GetMapping("/registration_confirming")
    public String tokenConfirmationProcessPage(@RequestParam("token") String token) {
        return registrationConfirmationTokenService.confirmToken(token);
    }

    @GetMapping("/reset_password_email_validation")
    public String openEmailValidatorForPasswordResetPage(Model model) {
        model.addAttribute("emailValidator", new EmailValidatorDto());
        return "email_validate_for_pass_reset";
    }

    @PostMapping("/reset_password_email_validation")
    public String passwordResetEmailValidatingAction(@Valid
                                                     @ModelAttribute("emailValidator")
                                                             EmailValidatorDto emailValidatorDto,
                                                     BindingResult bindingResult
    ) {
        return resetPasswordTokenConfirmationService.confirmEmail(emailValidatorDto, bindingResult);
    }

    @GetMapping("/reset_info")
    public String registrationSuccessPage() {
        return "reset_password_email_confirmed";
    }

    @GetMapping("/new_password")
    public String newPasswordCreatingPage(@RequestParam("token") String token, Model model) {
        String email = resetPasswordTokenConfirmationService.confirmTokenAndReturnEmail(token);
        model.addAttribute("passwordResetDto", new PasswordResetDto(email, token));
        return "new_password_form";
    }

    @PostMapping("/new_password")
    public String newPasswordConfirmed(@Valid
                                       @ModelAttribute("passwordResetDto")
                                               PasswordResetDto passwordResetDto,
                                       BindingResult bindingResult
    ) {
        return userService.changeUserPassword(passwordResetDto, bindingResult);
    }

}