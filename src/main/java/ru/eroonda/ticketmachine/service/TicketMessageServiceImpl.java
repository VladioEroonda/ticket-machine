package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.eroonda.ticketmachine.repository.TicketMessageRepository;

public class TicketMessageServiceImpl implements TicketMessageService{
    @Autowired
    TicketMessageRepository ticketMessageRepository;
}
