package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.TicketDto;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.TicketMessage;
import ru.eroonda.ticketmachine.enums.TicketStatus;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;
import ru.eroonda.ticketmachine.service.TicketService;

import java.time.LocalDateTime;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Ticket getTicketById(int ticketId){
        return ticketRepository.findById(ticketId).get();
    }

    @Transactional
    @Override
    public void addNewTicket(TicketDto ticketDto, int userId){
        Ticket ticket = new Ticket(
                LocalDateTime.now(),
                userRepository.getById(userId),
                TicketStatus.NEW,
                ticketDto.getTicketSubject(),
                new TicketMessage(ticketDto.getTicketMessage())
                );
        ticketRepository.save(ticket);
    }


}