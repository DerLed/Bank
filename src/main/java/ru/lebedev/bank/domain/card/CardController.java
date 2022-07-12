package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.card.dto.CardDTO;
import ru.lebedev.bank.exception.CardNotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    public CardDTO create(@RequestBody @Valid CardDTO card) {
        return cardService.save(card);
    }

    @PutMapping
    public CardDTO update(@RequestBody @Valid CardDTO card) {
        return cardService.save(card);
    }

    @GetMapping(value = "/user/{id}")
    public List<CardDTO> getAllByUserId(@PathVariable Long id) {
        return cardService.findByClientId(id);
    }

    @GetMapping(value = "/{id}")
    public CardDTO getById(@PathVariable Long id) {
        return cardService.findById(id).orElseThrow(() -> new CardNotFoundException(id));
    }
}
