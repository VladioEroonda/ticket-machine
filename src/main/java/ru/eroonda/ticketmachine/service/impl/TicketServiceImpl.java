package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    @Override
    public Ticket getTicketById(int ticketId){
        return ticketRepository.findById(ticketId).get();
    }

}