package ru.lebedev.bank.domain.cardPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/card-plans")
@RequiredArgsConstructor
public class CardPlanController {
    private final CardPlanService cardPlanService;

    @GetMapping
    public ResponseEntity<List<CardPlanDTO>> findAll(){
        List<CardPlanDTO> cardPlans = cardPlanService.findAll();
        return new ResponseEntity<>(cardPlans, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CardPlanDTO> create(@RequestBody CardPlanDTO cardPlanDTO){
        CardPlanDTO responseDTO = cardPlanService.save(cardPlanDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        cardPlanService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CardPlanDTO> findById(@PathVariable Long id) {
        Optional<CardPlanDTO> cardPlanDTO = cardPlanService.findById(id);
        return cardPlanDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
