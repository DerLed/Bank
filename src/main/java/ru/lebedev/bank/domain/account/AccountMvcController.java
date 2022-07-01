package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;
import ru.lebedev.bank.domain.client.ClientDTO;
import ru.lebedev.bank.domain.client.ClientService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountMvcController {
    private final AccountService accountService;
    private final ClientService clientService;
    private final AccountPlanService accountPlanService;

    @GetMapping("/client")
    public String account(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLogin(principal.getName());
        model.addAttribute("accounts", accounts);
        return "accounts/accounts";
    }

    @GetMapping("/new")
    public String newAccount(Model model, Principal principal){
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
        AccountDTO accountDTO = AccountDTO.builder()
                .client(clientDTO)
                .isDefault(false)
                .isClosed(false)
                .build();
        model.addAttribute("account", accountDTO);
        model.addAttribute("accountPlans", accountPlanService.findAll());
        return "accounts/account-new";
    }

    @PostMapping("/new")
    public String newAccountCreate(@ModelAttribute("account") @Valid AccountDTO accountDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "accounts/account-new";
        }
        accountService.save(accountDTO);
        return "redirect:/client";
    }
}
