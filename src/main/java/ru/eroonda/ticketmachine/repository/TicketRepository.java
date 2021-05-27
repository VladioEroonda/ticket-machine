package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    public Ticket findByClient(User client);
}
