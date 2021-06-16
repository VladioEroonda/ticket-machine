package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.service.TicketService;

import java.util.List;

@Controller
@RequestMapping("/ticket_machine/engineer")
public class EngineerController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/alltickets")
    public String openEngineerTicketListPage(@RequestParam(name = "filter", required = false) String filter, Model model){

        List<Ticket> ticketList = ticketService.getSortedTicketList(filter);

        model.addAttribute("allTickets", ticketList);
        model.addAttribute("filter", "");

        return "engineer_tickets_list";
    }

    @PostMapping("/alltickets")
    public String filterTicketListPageForEngineer(@RequestParam(name = "filter") String filter, Model model){
        System.out.println("filter at postmapping is" + filter);

        List<Ticket> ticketList = ticketService.getSortedTicketList(filter);
        model.addAttribute("allTickets", ticketList);

        return "redirect:alltickets?filter="+filter;
    }

}
