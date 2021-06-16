package ru.eroonda.ticketmachine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.dto.TicketDto;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.TicketMessage;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.enums.TicketStatus;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;
import ru.eroonda.ticketmachine.service.TicketService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Ticket> getSortedTicketList(String filter){

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int engineerId=principal.getId();
        System.out.println(filter);

        List<Ticket> resultList = new ArrayList<>();

        if(filter!=null) {
            switch (filter) {
                case "withoutEngineer":
                    resultList = ticketRepository.findTicketsByEngineerNull();
                    break;
                case "whereIamEngineer":
                    resultList = ticketRepository.findTicketsByEngineer(userRepository.getById(engineerId));
                    break;
                case "allNotClosedTickets":
                    resultList = ticketRepository.findAllByClosingTimeNull();
                    break;
                case "allTickets":
                    resultList = ticketRepository.findAllByCreationTimeIsNotNull();
                    break;
            }
        } else {
            resultList = ticketRepository.findTicketsByEngineer(userRepository.getById(engineerId));
        }

        System.out.println("RESULT SIZE:::::" + resultList);

        return resultList;
    }

}