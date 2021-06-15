package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.dto.CommentValidatorDto;
import ru.eroonda.ticketmachine.entity.Ticket;
import ru.eroonda.ticketmachine.entity.TicketMessage;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.service.TicketCommentService;
import ru.eroonda.ticketmachine.service.TicketService;
import ru.eroonda.ticketmachine.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/ticket_machine")
public class SystemController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketCommentService ticketCommentService;

    @GetMapping("")
    public String openMainSystemPage(Model model) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("get session user id" + principal.getId());
        List<Ticket> userTicketList = userService.findTicketsByClientId(principal.getId());
        System.out.println("getting list size: " + userTicketList.size());
        System.out.println("is that list null? " + (userTicketList == null));

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
        model.addAttribute("ticket", ticketService.getTicketById(ticketId));
        model.addAttribute("comments", ticketCommentService.getCommentsById(ticketId));
        model.addAttribute("addNewComment", new CommentValidatorDto());

        return "ticket_info";
    }

    @PostMapping("/search")
    public String startSearchMechanic() {
//TODO:реализовать механику поиска (мб по номеру или тексту)
        return "search_result_list";
    }

    @PostMapping("/new_comment")
    public String addNewTicketComment(@Valid @ModelAttribute("addNewComment")
                                      CommentValidatorDto commentValidatorDto,
                                      BindingResult bindingResult) {
        //TODO:обработать довление коммента и возврат на страницу, мб нужно добавлять хайден поле с айди тикета
        return "";
    }

}
