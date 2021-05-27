package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.TicketMessage;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/test")
    public String test(Model model){
        List<Ticket> ticketList = ticketRepository.findAll();
        User someUser = userRepository.getById(1);
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("user", someUser);
        return "test";
    }

    @RequestMapping("/test-register")
    public String testTicketRegisterPage(Model model){

        Ticket ticket = new Ticket();
        ticket.setMessage(new TicketMessage());

        model.addAttribute("ticket", new Ticket());
        return "test-register";
    }

    @PostMapping("ticket-submit")
    public String ticketSubmitting(@ModelAttribute("ticket") Ticket ticket){
        System.out.println(ticket.getSubject());
        System.out.println(ticket.getMessage().getMessageText());
        return "redirect:/";
    }


}
