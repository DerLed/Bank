package ru.lebedev.bank.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.card.dto.CardDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardDTO> create(@RequestBody @Valid CardDTO card) {
        CardDTO savedCard = cardService.save(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CardDTO> update(@RequestBody @Valid CardDTO card) {
        CardDTO savedCard = cardService.save(card);
        return new ResponseEntity<>(savedCard, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<CardDTO>> getAllByUserId(@PathVariable Long id) {
        List<CardDTO> cards = cardService.findByClientId(id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CardDTO> getById(@PathVariable Long id) {
        Optional<CardDTO> cardDTO = cardService.findById(id);
        return cardDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
