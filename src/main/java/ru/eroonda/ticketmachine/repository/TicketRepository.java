package ru.eroonda.ticketmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findTicketsByClient(User client);
    List<Ticket> findTicketsByClientId(int userClientId);
    List<Ticket> findTicketsByEngineer(User engineer);
    List<Ticket> findTicketsByEngineerNull();
    List<Ticket> findAllByCreationTimeIsNotNull();
    List<Ticket> findAllByClosingTimeNull();
}
