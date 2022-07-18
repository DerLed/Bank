package ru.lebedev.bank.domain.client;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.client.dto.ClientCreateReq;
import ru.lebedev.bank.exception.UserAlreadyExistException;
import ru.lebedev.bank.validator.ClientCreateReqValidator;


import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientMvcController {

    private final ClientService clientService;
    private final ClientCreateReqValidator clientCreateReqValidator;

    @InitBinder("clientCreate")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(clientCreateReqValidator);
    }

    @GetMapping
    public String client(Model model, Principal principal) {
        model.addAttribute("client", clientService.findByUserLogin(principal.getName()).orElseThrow());
        return "client/client";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Форма входа");
        return "client/login";
    }

    @GetMapping("/new")
    public String signup(Model model) {
        model.addAttribute("clientCreate", new ClientCreateReq());
        return "client/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("clientCreate") @Valid ClientCreateReq clientCreate, BindingResult bindingResult) throws UserAlreadyExistException {
        if (bindingResult.hasErrors()) {
            return "client/new";
        }

        clientService.newClient(clientCreate);

        return "redirect:/";
    }

}
