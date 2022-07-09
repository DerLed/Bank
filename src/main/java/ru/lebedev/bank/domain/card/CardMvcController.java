package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.card.dto.CardCreateDTO;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;
import ru.lebedev.bank.domain.cardPlan.CardPlanService;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardMvcController {
    private final CardService cardService;
//    private final AccountService accountService;
//    private final CardPlanService cardPlanService;
    private final ClientService clientService;

    @GetMapping
    public String allCards(Model model, Principal principal){
        List<CardDTO> cards = cardService.findByClientUserLogin(principal.getName());
        model.addAttribute("cards", cards);
        return "card/card-list";
    }

    @GetMapping("/new")
    public String newCard(Model model){
        model.addAttribute("newCard", new CardCreateDTO());

        return "card/card-new";
    }




    /**
     *
     * @param cardDTO
     * @param bindingResult
     * @param principal
     * @param model
     * @return
     */
    @PostMapping("/new")
    public String newCardCreate(@ModelAttribute("newCard") @Valid CardCreateDTO cardDTO,
                                   BindingResult bindingResult, Principal principal, Model model){
//        if (bindingResult.hasErrors()) {
//            List<AccountDTO> accounts = accountService.findByClientLoginAndType(principal.getName(), TypeAccount.CHECKING);
//            List<CardPlanDTO> cardPlans = cardPlanService.findAll();
//            model.addAttribute("accounts", accounts);
//            model.addAttribute("cardPlans", cardPlans);
//            return "card/card-new";
//        }
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
//        CardDTO savedCard = CardDTO.builder()
//                .clientDTO(clientDTO)
//                .cardPlanDTO(cardDTO.getCardPlanDTO())
//                .accountDTO(cardDTO.getAccountDTO())
//                .build();
        cardDTO.setClientDTO(clientDTO);
        cardService.create(cardDTO);
        return "redirect:/client";
    }
}
