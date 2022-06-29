package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lebedev.bank.domain.client.ClientCreateReq;
import ru.lebedev.bank.domain.client.ClientDTO;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account_plan")
@RequiredArgsConstructor
public class AccountPlanMvcController {

    private final AccountPlanService accountPlanService;

    @GetMapping()
    public String accountPlan(Model model){

        List<AccountPlanDTO> accountPlans = accountPlanService.findAll();
        model.addAttribute("accountPlan", accountPlans);

        return "account-plan";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("accountPlan", new AccountPlanDTO());
        return "account-plan-add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("accountPlan") @Valid AccountPlanDTO accountPlanDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "account-plan-add";
        }
        accountPlanService.save(accountPlanDTO);
        return "redirect:/account_plan";
    }
}
