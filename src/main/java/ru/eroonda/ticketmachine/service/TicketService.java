package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket getTicketById(int ticketId){
        return ticketRepository.findById(ticketId).get();
    }

}
