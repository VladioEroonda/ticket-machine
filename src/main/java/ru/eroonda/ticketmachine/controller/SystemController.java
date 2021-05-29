package ru.eroonda.ticketmachine.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket_machine")
public class SystemController {

    @GetMapping("")
    public String openMainSystemPage(){
        return "ticket_machine";
    }
}
