package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.RegistrationConfirmationToken;
import ru.eroonda.ticketmachine.entity.ResetPasswordConfirmationToken;

import java.util.Optional;

@Repository
public interface ResetPasswordConfirmationTokenRepository extends JpaRepository<ResetPasswordConfirmationToken, Integer> {
    Optional<ResetPasswordConfirmationToken> findByToken(String token);
    Optional<ResetPasswordConfirmationToken> findByUserEmail(String email);
}
