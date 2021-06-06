package ru.eroonda.ticketmachine.service;

import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.EmailValidatorDto;
import ru.eroonda.ticketmachine.dto.PasswordResetDto;

public interface ResetPasswordTokenConfirmationService {
    String confirmEmail(EmailValidatorDto emailValidatorDto, BindingResult result);
    String confirmTokenAndReturnEmail(String token);
}
