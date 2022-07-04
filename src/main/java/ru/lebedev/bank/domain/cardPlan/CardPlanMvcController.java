package ru.lebedev.bank.domain.cardPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.cardPlan.dto.CardPlanDTO;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/card-plan")
@RequiredArgsConstructor
public class CardPlanMvcController {
    private final CardPlanService cardPlanService;

    @GetMapping()
    public String accountPlan(Model model){
        List<CardPlanDTO> accountPlans = cardPlanService.findAll();
        model.addAttribute("accountPlans", accountPlans);
        return "card-plan/card-plan";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("cardPlan", new CardPlanDTO());
        return "card-plan/card-plan-add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("accountPlan") @Valid CardPlanDTO cardPlanDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "card-plan/card-plan-add";
        }
        cardPlanService.save(cardPlanDTO);
        return "redirect:/card_plan";
    }

    @GetMapping("/edit/{id}")
    public String editAccountPlan(@PathVariable("id") Long id, Model model) {
        CardPlanDTO accountPlanDTO = cardPlanService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid accountPlan Id:" + id));
        model.addAttribute("accountPlan", accountPlanDTO);
        model.addAttribute("type", Arrays.asList(TypeAccount.values()));
        return "card-plan/card-plan-edit";
    }

    @PostMapping("/update/{id}")
    public String updateAccountPlan(@PathVariable("id") Long id, @Valid CardPlanDTO cardPlanDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            cardPlanDTO.setId(id);
            return "card-plan/card-plan-edit";
        }

        cardPlanService.save(cardPlanDTO);
        return "redirect:/account_plan";
    }

    @GetMapping("/remove/{id}")
    public String removeAccountPlan(@PathVariable("id") Long id, Model model) {
        cardPlanService.deleteById(id);
        return "redirect:/account_plan";
    }
}
