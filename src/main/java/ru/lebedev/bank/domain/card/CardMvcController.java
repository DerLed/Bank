package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.account.AccountDTO;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.cardPlan.CardPlanDTO;
import ru.lebedev.bank.domain.cardPlan.CardPlanService;
import ru.lebedev.bank.domain.client.ClientDTO;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardMvcController {
    private final CardService cardService;
    private final AccountService accountService;
    private final CardPlanService cardPlanService;

    @GetMapping
    public String accountsLoan(Model model, Principal principal){
        List<CardDTO> cards = cardService.findByClientUserLogin(principal.getName());
        model.addAttribute("cards", cards);
        return "card/card-list";
    }

    @GetMapping("/new")
    public String newCard(Model model, Principal principal){
        List<AccountDTO> accounts = accountService.findByClientLoginCheckingAccounts(principal.getName());
        List<CardPlanDTO> cardPlans = cardPlanService.findAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("cardPlans", accounts);
        model.addAttribute("newCard", new CardCreateDTO());

        return "accounts/account-new";
    }

//    @PostMapping("/new")
//    public String newAccountCreate(@ModelAttribute("account") @Valid AccountDTO accountDTO,
//                                   BindingResult bindingResult, Principal principal){
//        if (bindingResult.hasErrors()) {
//            return "accounts/account-new";
//        }
//        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
//        accountDTO.setClient(clientDTO);
//        accountService.save(accountDTO);
//        return "redirect:/client";
//    }
}
