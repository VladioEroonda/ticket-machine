package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.entity.ConfirmationToken;
import ru.eroonda.ticketmachine.repository.ConfirmationTokenRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    @Override
    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenRepository
                .findByToken(token)
                .orElseThrow(()->
                        new IllegalArgumentException("token not found"));
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalArgumentException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("token expired");// PROTUH TVOY PAROL(c)
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);
        userService.enableUserAccount(confirmationToken.getUser().getEmail());

        return "token_confirmed";
    }

}
