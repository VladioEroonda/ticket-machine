package ru.eroonda.ticketmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String homePage(Model model){
        return "index";
    }

    @GetMapping("/about")
    public String aboutPage(Model model){
        return "about";
    }

}
