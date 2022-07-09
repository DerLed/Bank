package ru.lebedev.bank.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.lebedev.bank.domain.account.checking.CheckingAccount;
import ru.lebedev.bank.domain.account.checking.CheckingAccountService;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.account.saving.SavingAccountService;
import ru.lebedev.bank.domain.accountPlan.AccountPlanService;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.domain.transaction.dto.TransactionFormDTO;
import ru.lebedev.bank.validator.SavingAccountCreateDTOValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/saving")
@RequiredArgsConstructor
public class SavingAccountMvcController {
    private final SavingAccountService savingAccountService;
    private final ClientService clientService;
    private final AccountPlanService accountPlanService;
    private final CheckingAccountService checkingAccountService;
    private final SavingAccountCreateDTOValidator accountCreateValidator;


    @InitBinder
    public void initBinder(WebDataBinder binder) {

        if (binder.getTarget() == null) return;
        if (accountCreateValidator.supports(binder.getTarget().getClass())) {
                binder.addValidators(accountCreateValidator);
        }


    }

    @GetMapping
    public String showAll(Model model, Principal principal) {
        List<SavingAccountDTO> accounts = savingAccountService.findByClientLogin(principal.getName());
        model.addAttribute("accounts", accounts);
//        model.addAttribute("typeAccount", typeAccount.toString());
        return "accounts/saving/account-list";
    }

    @GetMapping("/new")
    public String newSavingAccount(Model model, Principal principal) {
        model.addAttribute("accountPlans", accountPlanService.findAll());
        model.addAttribute("accountCreate", new SavingAccountCreateDTO());
        List<CheckingAccountDTO> checkingAccounts = checkingAccountService.findByClientLogin(principal.getName());
        model.addAttribute("checkingAccounts", checkingAccounts);

        return "accounts/saving/new";
    }

    @PostMapping("/new")
    public String newSavingAccountCreate(@ModelAttribute("accountCreate")
                                         @Valid SavingAccountCreateDTO savingAccountCreateDTO,
                                         BindingResult bindingResult,
                                         Principal principal,
                                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountPlans", accountPlanService.findAll());
            List<CheckingAccountDTO> checkingAccounts = checkingAccountService.findByClientLogin(principal.getName());
            model.addAttribute("checkingAccounts", checkingAccounts);
            return "accounts/saving/new";
        }
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
        savingAccountCreateDTO.setClientDTO(clientDTO);
        savingAccountService.create(savingAccountCreateDTO);
        return "redirect:/client";
    }


    @GetMapping("/close/{accountId}")
    public String viewClose(@PathVariable Long accountId, Principal principal, Model model){
        SavingAccountDTO closeAccount = savingAccountService.findById(accountId).orElseThrow();
        List<SavingAccountDTO> allAccounts = savingAccountService.findByClientLogin(principal.getName());
        boolean isOwner = allAccounts.stream().anyMatch(a -> a.getId().equals(closeAccount.getId()));
        if(isOwner) {
            model.addAttribute("closeAccount", closeAccount);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
        return "accounts/saving/close";
    }

    @PostMapping("/close/{accountId}")
    public String close (@PathVariable Long accountId, Principal principal) {

        SavingAccountDTO closeAccount = savingAccountService.findById(accountId).orElseThrow();
        List<SavingAccountDTO> allAccounts = savingAccountService.findByClientLogin(principal.getName());
        boolean isOwner = allAccounts.stream().anyMatch(a -> a.getId().equals(closeAccount.getId()));
        if(isOwner) {

            savingAccountService.deleteById(accountId);
        }

        return "redirect:/client";
    }

}
