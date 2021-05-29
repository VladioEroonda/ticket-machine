package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getUserTickets(User user) {

        List<Ticket> allUserTickets = ticketRepository.findByClient(user);

        if(allUserTickets==null){
            allUserTickets=new ArrayList<>();
        }

        return ticketRepository.findByClient(user);
    }

    public User getUserById(int userId) {
       return userRepository.getById(userId);
    }
}
