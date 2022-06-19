package ru.lebedev.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.repository.ClientRepo;


import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/person")
    public String personList(Model model){
        List<Client> lp  = clientRepo.findAll();
        model.addAttribute("lp", lp);
        return "person-list";
    }

    @PostMapping("/addclient")
    public String addClient(){
        return "add-client";
    }


}
