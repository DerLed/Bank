package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/account-plan")
@RequiredArgsConstructor
public class AccountPlanMvcController {

    private final AccountPlanService accountPlanService;

    @GetMapping()
    public String accountPlan(Model model){
        List<AccountPlanDTO> accountPlans = accountPlanService.findAll();
        model.addAttribute("accountPlans", accountPlans);
        return "account-plan/account-plan";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("accountPlan", new AccountPlanDTO());
        model.addAttribute("type", Arrays.asList(TypeAccount.values()));
        return "account-plan/account-plan-add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("accountPlan") @Valid AccountPlanDTO accountPlanDTO,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("type", Arrays.asList(TypeAccount.values()));
            return "account-plan/account-plan-add";
        }
        accountPlanService.save(accountPlanDTO);
        return "redirect:/account-plan";
    }

    @GetMapping("/edit/{id}")
    public String editAccountPlan(@PathVariable("id") Long id, Model model) {
        AccountPlanDTO accountPlanDTO = accountPlanService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid accountPlan Id:" + id));
        model.addAttribute("accountPlan", accountPlanDTO);
        model.addAttribute("type", Arrays.asList(TypeAccount.values()));
        return "account-plan/account-plan-edit";
    }

    @PostMapping("/update/{id}")
    public String updateAccountPlan(@PathVariable("id") Long id, @Valid AccountPlanDTO accountPlanDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            accountPlanDTO.setId(id);
            return "account-plan/account-plan-edit";
        }

        accountPlanService.save(accountPlanDTO);
        return "redirect:/account-plan";
    }

    @GetMapping("/remove/{id}")
    public String removeAccountPlan(@PathVariable("id") Long id, Model model) {
        accountPlanService.deleteById(id);
        return "redirect:/account-plan";
    }
}
