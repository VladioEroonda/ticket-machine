package ru.eroonda.ticketmachine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eroonda.ticketmachine.entity.User;



@Controller
public class MainController {

    @GetMapping("/")
    public String homePage(Model model){ // можно ++ реквест парам по гету или посту name например и из него сделать строчку вида
        return "index";
    }

}
