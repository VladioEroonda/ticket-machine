package ru.eroonda.ticketmachine.service;

import ru.eroonda.ticketmachine.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    String confirmToken(String token);
}
