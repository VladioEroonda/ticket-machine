package ru.eroonda.ticketmachine.service;

import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.TicketDto;
import ru.eroonda.ticketmachine.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getTicketById(int ticketId);
    void addNewTicket(TicketDto ticketDto, int userId);
    List<Ticket> getSortedTicketList(String filter);
}
