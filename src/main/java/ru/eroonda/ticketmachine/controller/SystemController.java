package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.eroonda.ticketmachine.dto.CommentDto;
import ru.eroonda.ticketmachine.dto.TicketDto;
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

    @GetMapping("/new_ticket")
    public String openNewTicketCreatePage(Model model) {
        model.addAttribute("newTicket", new TicketDto());
        return "new_ticket";
    }

    @PostMapping("/new_ticket")
    public String newTicketCreationHandler(@Valid @ModelAttribute("newTicket") TicketDto ticketDto,
                                           BindingResult bindingResult) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ticketService.addNewTicket(ticketDto, principal.getId());

        if (bindingResult.hasErrors()) {
            return "new_ticket";
        }

        return "redirect:/ticket_machine";
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
        model.addAttribute("addNewComment", new CommentDto());

        return "ticket_info";
    }

    @PostMapping("/search")
    public String startSearchMechanic() {
         //TODO:?????????????????????? ???????????????? ???????????? (???? ???? ???????????? ?????? ????????????)
        return "search_result_list";
    }

    @PostMapping("/new_comment")
    public String addNewTicketComment(@RequestParam(name = "tic_id") int ticketId,
            @Valid @ModelAttribute("addNewComment")
                                              CommentDto commentDto,
                                      BindingResult bindingResult) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ticketCommentService.addNewComment(commentDto, ticketId, principal.getId());

        bindingResult.hasErrors();

        return "redirect:ticket_info/"+ticketId;
    }

}
