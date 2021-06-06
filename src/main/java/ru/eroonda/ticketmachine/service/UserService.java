package ru.eroonda.ticketmachine.service;

import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.PasswordResetDto;
import ru.eroonda.ticketmachine.dto.UserDto;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;

import java.util.List;

public interface UserService {
    List<Ticket> findTicketsByClientId(int userClientId);
    User getUserById(int userId);
    User findByEmail(String email);
    String addUser(UserDto user, BindingResult bindingResult);
    String changeUserPassword(PasswordResetDto passwordResetDto, BindingResult bindingResult);
    void enableUserAccount(String email);
}