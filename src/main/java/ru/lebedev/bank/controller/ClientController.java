package ru.lebedev.bank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.repository.ClientRepo;
import ru.lebedev.bank.service.ClientService;


import java.util.List;

@Controller
@RequestMapping("/client")

public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String client(Model model){
        List<Client> lp  = clientService.all();
        model.addAttribute("lp", lp);
        return "client";
    }

    @GetMapping("/new")
    public String newClient(Model model){
        model.addAttribute("client", new Client());
        return "client/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") Client client){
        clientService.save(client);
        return "redirect:client";
    }

}
