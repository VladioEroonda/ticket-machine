package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.RegistrationConfirmationToken;

import java.util.Optional;

@Repository
public interface RegistrationConfirmationTokenRepository extends JpaRepository<RegistrationConfirmationToken, Integer> {
    Optional<RegistrationConfirmationToken> findByToken(String token);
}
