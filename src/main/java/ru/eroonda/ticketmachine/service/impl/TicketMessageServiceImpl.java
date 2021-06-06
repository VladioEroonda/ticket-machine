package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.repository.TicketMessageRepository;
import ru.eroonda.ticketmachine.service.TicketMessageService;

@Service
public class TicketMessageServiceImpl implements TicketMessageService {
    @Autowired
    private TicketMessageRepository ticketMessageRepository;
}
