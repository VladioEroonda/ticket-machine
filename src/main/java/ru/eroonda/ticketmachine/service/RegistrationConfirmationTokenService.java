package ru.eroonda.ticketmachine.service;

import ru.eroonda.ticketmachine.entity.RegistrationConfirmationToken;

public interface RegistrationConfirmationTokenService {
    void saveConfirmationToken(RegistrationConfirmationToken token);
    String confirmToken(String token);
}
