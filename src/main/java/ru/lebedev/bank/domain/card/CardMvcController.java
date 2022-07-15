package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.card.dto.CardCreateDTO;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.domain.card.dto.TransactionCardFormDTO;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.exception.AccountTransferException;
import ru.lebedev.bank.validator.TransactionCardFormDTOValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardMvcController {
    private final CardService cardService;
    private final AccountService accountService;
    private final ClientService clientService;
    private final TransactionCardFormDTOValidator transactionCardFormDTOValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(transactionCardFormDTOValidator);
    }

    @GetMapping
    public String allCards(Model model, Principal principal) {
        List<CardDTO> cards = cardService.findByClientLogin(principal.getName());
        model.addAttribute("cards", cards);
        return "card/card-list";
    }

    @GetMapping("/new")
    public String newCard(Model model) {
        model.addAttribute("newCard", new CardCreateDTO());
        return "card/card-new";
    }


    /**
     * @param cardDTO
     * @param bindingResult
     * @param principal
     * @param model
     * @return
     */
    @PostMapping("/new")
    public String newCardCreate(@ModelAttribute("newCard") @Valid CardCreateDTO cardDTO,
                                BindingResult bindingResult, Principal principal, Model model) {
        ClientDTO clientDTO = clientService.findByUserLogin(principal.getName()).orElseThrow();
        cardDTO.setClientDTO(clientDTO);
        cardService.create(cardDTO);

        return "redirect:/client";
    }

    @GetMapping("/transfer")
    public String showCardTransfer(Model model, Principal principal) {
        List<CardDTO> cards = cardService.findByClientLogin(principal.getName());
        model.addAttribute("form", new TransactionCardFormDTO());
        model.addAttribute("cards", cards);
        return "card/card-transfer";
    }

    @PostMapping("/transfer")
    public String cardTransfer(@ModelAttribute("form") @Valid TransactionCardFormDTO transactionCardFormDTO,
                                   BindingResult bindingResult,
                                   Principal principal, Model model) {
        if (bindingResult.hasErrors()) {
            List<CardDTO> cards = cardService.findByClientLogin(principal.getName());
            model.addAttribute("cards", cards);
            return "card/card-transfer";
        }

        accountService.transferMoneyByCardNumber(transactionCardFormDTO.getCardDTO().getAccountDTO().getId(),
                transactionCardFormDTO.getCardNumber(), transactionCardFormDTO.getAmount());

        return "redirect:/client";
    }

}
