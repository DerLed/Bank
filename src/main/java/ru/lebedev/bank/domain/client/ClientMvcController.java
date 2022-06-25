package ru.lebedev.bank.domain.client;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientMvcController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientMvcController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String client(Model model){
        List<ClientDTO> lp  = clientService.findAll();
        model.addAttribute("lp", lp);
        return "client";
    }





    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Форма входа");
        return "client/login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("client", new ClientDTO());
        return "client/signup";
    }

    @PostMapping("/signup")
    public String create(@ModelAttribute("client") ClientDTO clientDTO){
        clientService.save(clientDTO);
        return "redirect:";
    }

}
