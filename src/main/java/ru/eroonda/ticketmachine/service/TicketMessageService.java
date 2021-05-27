package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.repository.TicketMessageRepository;

@Service
public class TicketMessageService {
    @Autowired
    private TicketMessageRepository repository;
}
