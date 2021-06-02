package ru.eroonda.ticketmachine.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.TicketMessage;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.service.TicketService;
import ru.eroonda.ticketmachine.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/ticket_machine")
public class SystemController {
    @Autowired
    UserService userService;//да-да так никто не делает, потом отрефакторю
    @Autowired
    TicketService ticketService;//да-да так никто не делает, потом отрефакторю

    @GetMapping("")
    public String openMainSystemPage(Model model) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal.getEmail());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        //TODO: временно пока не узнаю как получать юзера из сессии
        User user = userService.getUserById(2);
        //
        List<Ticket> userTicketList = userService.getUserTickets(user);
        System.out.println(userTicketList.size());

        model.addAttribute("userTickets", userTicketList);

        return "ticket_machine";
    }

    @GetMapping("/new")
    public String openNewTicketCreatePage(Model model) {
        model.addAttribute("newTicket", new Ticket(new TicketMessage()));
        return "new_ticket";
    }

    @PostMapping("/new")
    public String newTicketCreationHandler(@ModelAttribute("newTicket") Ticket ticket) {
        //TODO:Тут обработать, задать статус new, вставить дату .now, задать юзера
        return "";
    }

    @GetMapping("/engineer_info/{id}")
    public String openUserInfoPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("engineerInfo", userService.getUserById(id));
        return "engineer_info";
    }

    @GetMapping("/ticket_info/{id}")
    public String openTicketInfoPage(@PathVariable("id") int ticketId, Model model) {
//        model.addAttribute("engineerInfo", userService.getUserById(id));
        model.addAttribute("ticket", ticketService.getTicketById(ticketId));
        return "ticket_info";
    }

    @PostMapping("/search")
    public String startSearchMechanic() {
//TODO:реализовать механику поиска (мб по номеру или тексту)
        return "search_result_list";
    }


}
