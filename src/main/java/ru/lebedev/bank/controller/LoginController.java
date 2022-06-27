package ru.lebedev.bank.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.client.ClientDTO;
import ru.lebedev.bank.domain.client.ClientService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private final ClientService clientService;
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Форма входа");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("client", new ClientDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String create(@ModelAttribute("client") ClientDTO clientDTO){
        clientService.save(clientDTO);
        return "redirect:";
    }
}

