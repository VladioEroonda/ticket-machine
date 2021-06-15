package ru.eroonda.ticketmachine.service;

import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.TicketDto;
import ru.eroonda.ticketmachine.entity.Ticket;

public interface TicketService {
    Ticket getTicketById(int ticketId);
    void addNewTicket(TicketDto ticketDto, int userId);
}
