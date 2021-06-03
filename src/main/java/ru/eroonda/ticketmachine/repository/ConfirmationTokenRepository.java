package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.ConfirmationToken;
import ru.eroonda.ticketmachine.entity.User;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findByToken(String token);
}
