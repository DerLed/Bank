package ru.lebedev.bank.domain.client;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.client.dto.ClientCreateReq;
import ru.lebedev.bank.exception.UserAlreadyExistException;


import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientMvcController {

    private final ClientService clientService;

    @GetMapping
    public String client(Model model, Principal principal){
        model.addAttribute("client", clientService.findByUserLogin(principal.getName()).orElseThrow());
        return "client/client";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Форма входа");
        return "client/login";
    }

    @GetMapping("/new")
    public String signup(Model model){
        model.addAttribute("client", new ClientCreateReq());
        return "client/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("client") @Valid ClientCreateReq clientCreate, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "client/new";
        }

        try {
            clientService.newClient(clientCreate);
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("phoneNumber", "client.phoneNumber","Данный номер уже зарегестрирован в системе.");
            model.addAttribute("client", clientCreate);
            return "client/new";
        }

        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String login(Model model) {
//        model.addAttribute("title", "Форма входа");
//        return "client/login";
//    }



}
