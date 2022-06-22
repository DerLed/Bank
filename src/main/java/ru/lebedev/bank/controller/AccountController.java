package ru.lebedev.bank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.entity.Account;
import ru.lebedev.bank.service.AccountService;

import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @GetMapping
    public String account(Model model){
        List<Account> la = accountService.findAll();
        model.addAttribute("account_list", la);
        return "account";
    }
}