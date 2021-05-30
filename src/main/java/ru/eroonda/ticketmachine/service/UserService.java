package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


public interface UserService {
    public List<Ticket> getUserTickets(User user);
    public User getUserById(int userId);
    public User findByEmail(String email);
    public boolean addUser(User user);
}
