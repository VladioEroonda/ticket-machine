package ru.eroonda.ticketmachine.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eroonda.ticketmachine.entity.Role;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.enums.UserRoles;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<Ticket> getUserTickets(User user) {

        List<Ticket> allUserTickets = ticketRepository.findByClient(user);

        if(allUserTickets==null){
            allUserTickets=new ArrayList<>();
        }
        return ticketRepository.findByClient(user);
    }

    @Override
    @Transactional
    public User getUserById(int userId) {
        return userRepository.getById(userId);
    }

    @Override
    @Transactional //TODO:??
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean addUser(@NotNull User user){
        User isUserAlreadyAtDB = userRepository.findByEmail(user.getEmail());
        if(!(isUserAlreadyAtDB==null)){
            return false;
        }
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1, UserRoles.ROLE_USER)));
        userRepository.save(user);
        return true;
    }
}