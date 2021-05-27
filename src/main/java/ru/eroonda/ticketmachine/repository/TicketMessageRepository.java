package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eroonda.ticketmachine.entity.TicketMessage;

public interface TicketMessageRepository extends JpaRepository <TicketMessage, Integer> {
}
