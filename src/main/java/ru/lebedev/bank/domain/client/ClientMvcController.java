package ru.lebedev.bank.domain.client;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.security.IAuthenticationFacade;
import ru.lebedev.bank.security.SecurityUser;


import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientMvcController {

    private final ClientService clientService;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public ClientMvcController(ClientServiceImpl clientService, IAuthenticationFacade authenticationFacade) {
        this.clientService = clientService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping()
    public String client(Model model){
        List<ClientDTO> lp  = clientService.findAll();
        model.addAttribute("lp", lp);
        return "client";
    }

    @GetMapping("/accounts")
    public String clientAccounts(Model model){
        Authentication authentication = authenticationFacade.getAuthentication();
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        List<ClientDTO> lp  = clientService.findAll();
        model.addAttribute("lp", lp);
        return "client";
    }




//    @PostMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("title", "Форма входа");
//        return "client/login";
//    }



}
