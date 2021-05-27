package ru.eroonda.ticketmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eroonda.ticketmachine.entity.User;
import ru.eroonda.ticketmachine.repository.TicketRepository;
import ru.eroonda.ticketmachine.repository.UserRepository;


@Controller
public class MainController {

    @RequestMapping("/")
    public String indexPage(){

        return "index";
    }

//    @RequestMapping("/register")
//    public String register(Model model){
//        model.addAttribute("user", new User());
//        return "register";
//    }

}
