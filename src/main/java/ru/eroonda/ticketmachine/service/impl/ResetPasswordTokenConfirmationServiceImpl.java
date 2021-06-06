package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.EmailValidatorDto;
import ru.eroonda.ticketmachine.email.EmailMessageBuilder;
import ru.eroonda.ticketmachine.email.EmailSender;
import ru.eroonda.ticketmachine.entity.ResetPasswordConfirmationToken;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.repository.ResetPasswordConfirmationTokenRepository;
import ru.eroonda.ticketmachine.service.ResetPasswordTokenConfirmationService;
import ru.eroonda.ticketmachine.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResetPasswordTokenConfirmationServiceImpl implements ResetPasswordTokenConfirmationService {
    @Autowired
    private UserService userService;
    @Autowired
    private ResetPasswordConfirmationTokenRepository resetPasswordConfirmationTokenRepository;
    @Autowired
    private EmailSender emailSender;

    @Override
    @Transactional
    public String confirmEmail(EmailValidatorDto emailValidatorDto, BindingResult bindingResult) {
        User user = userService.findByEmail(emailValidatorDto.getEmail());

        if (user == null) {
                bindingResult.rejectValue("email", "error.email",
                        "User account with this email not exist at base. Try again.");
        }

        if (bindingResult.hasErrors()){
            return "email_validate_for_pass_reset";
        }

        String token = UUID.randomUUID().toString();

        ResetPasswordConfirmationToken passwordConfirmationToken = new ResetPasswordConfirmationToken(
                token
                , LocalDateTime.now()
                , LocalDateTime.now().plusMinutes(15)
                , user
        );

        resetPasswordConfirmationTokenRepository.save(passwordConfirmationToken);

                emailSender.send(user.getEmail(),
                "Resetting your account password at TicketMachine",
                EmailMessageBuilder.getPasswordResettingMessage(
                        token,
                        user.getName(),
                        user.getSurname()));

        return "reset_password_email_confirmed";
    }

    @Override
    @Transactional
    public String confirmTokenAndReturnEmail(String token) {

        ResetPasswordConfirmationToken resetPasswordToken = resetPasswordConfirmationTokenRepository
                .findByToken(token)
                .orElseThrow(()->
                        new IllegalArgumentException("token not found"));

        if(resetPasswordToken.getConfirmedAt()!=null){
            throw new IllegalArgumentException("email already confirmed");
        }

        LocalDateTime expiredAt = resetPasswordToken.getExpiredAt();

        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("token expired");
        }

        return resetPasswordToken.getUser().getEmail();
    }

}
