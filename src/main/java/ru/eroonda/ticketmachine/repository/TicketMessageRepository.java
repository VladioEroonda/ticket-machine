package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.TicketMessage;

@Repository
public interface TicketMessageRepository extends JpaRepository <TicketMessage, Integer> {
}
