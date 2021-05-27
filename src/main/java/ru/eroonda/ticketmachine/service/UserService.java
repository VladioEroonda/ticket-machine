package ru.eroonda.ticketmachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eroonda.ticketmachine.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
