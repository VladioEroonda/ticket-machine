package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.entity.RegistrationConfirmationToken;
import ru.eroonda.ticketmachine.repository.RegistrationConfirmationTokenRepository;
import ru.eroonda.ticketmachine.service.RegistrationConfirmationTokenService;
import ru.eroonda.ticketmachine.service.UserService;

import java.time.LocalDateTime;

@Service
public class RegistrationConfirmationTokenServiceImpl implements RegistrationConfirmationTokenService {
    @Autowired
    private RegistrationConfirmationTokenRepository registrationConfirmationTokenRepository;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void saveConfirmationToken(RegistrationConfirmationToken token){
        registrationConfirmationTokenRepository.save(token);
    }

    @Override
    @Transactional
    public String confirmToken(String token){
        RegistrationConfirmationToken registrationConfirmationToken = registrationConfirmationTokenRepository
                .findByToken(token)
                .orElseThrow(()->
                        new IllegalArgumentException("token not found"));
        if(registrationConfirmationToken.getConfirmedAt()!=null){
            throw new IllegalArgumentException("email already confirmed");
        }

        LocalDateTime expiredAt = registrationConfirmationToken.getExpiredAt();

        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("token expired");
        }

        registrationConfirmationToken.setConfirmedAt(LocalDateTime.now());
        registrationConfirmationTokenRepository.save(registrationConfirmationToken);
        userService.enableUserAccount(registrationConfirmationToken.getUser().getEmail());

        return "registration_token_confirmed";
    }

}
