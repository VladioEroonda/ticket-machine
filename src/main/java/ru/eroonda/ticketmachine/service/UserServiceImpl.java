package ru.eroonda.ticketmachine.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.eroonda.ticketmachine.dto.UserDto;
import ru.eroonda.ticketmachine.email.EmailMessageBuilder;
import ru.eroonda.ticketmachine.email.EmailSender;
import ru.eroonda.ticketmachine.entity.ConfirmationToken;
import ru.eroonda.ticketmachine.entity.Role;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.enums.UserRoles;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    EmailSender emailSender;

    @Override
    @Transactional
    public List<Ticket> findTicketsByClientId(int userClientId) {
        List<Ticket> allUserTickets = ticketRepository.findTicketsByClientId(userClientId);
        if (allUserTickets == null) {
            allUserTickets = new ArrayList<>();
        }

        return allUserTickets;
    }


    @Override
    @Transactional
    public User getUserById(int userId) {
        return userRepository.getById(userId);
    }

    @Override
    @Transactional //TODO:??
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public String addUser(@NotNull UserDto userFromRequest, BindingResult bindingResult) {

        if (userRepository.findByEmail(userFromRequest.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email",
                    "User with this email already exist at base. Choose another email");
        }

        if (!userFromRequest.getPassword().equals(userFromRequest.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "error.passwordConfirm",
                    "This password does not match that entered in the password field, please try again.");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User validatedUser = new User(
                userFromRequest.getName(),
                userFromRequest.getSurname(),
                userFromRequest.getEmail(),
                passwordEncoder.encode(userFromRequest.getPassword()),
                userFromRequest.getPhoneNumber(),
                Collections.singleton(new Role(1, UserRoles.ROLE_USER))
        );

        userRepository.save(validatedUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                validatedUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        emailSender.send(validatedUser.getEmail(),
                "Confirm your account at TicketMachine",
                EmailMessageBuilder.getValidationMessage(
                        token,
                        validatedUser.getName(),
                        validatedUser.getSurname()));

        return "redirect:registration_success";
    }

    @Override
    @Transactional
    public void enableUserAccount(String email) {
        User user = findByEmail(email);
        user.setEnabled(true);
    }
}