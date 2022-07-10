package ru.lebedev.bank.validator;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lebedev.bank.domain.account.dto.SavingAccountCreateDTO;


import java.math.BigDecimal;

@Component
@NoArgsConstructor
public class SavingAccountCreateDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SavingAccountCreateDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SavingAccountCreateDTO createAccount = (SavingAccountCreateDTO) target;

        if (createAccount.getAccountPlanDTO() == null) {
            errors.rejectValue("accountPlanDTO", "accountPlan.error", "Не выбран продукт");
        }

        if (createAccount.getAccountPlanDTO() != null) {

            if (createAccount.getAmount() == null || createAccount.getAmount().compareTo(BigDecimal.ONE) < 0) {
                errors.rejectValue("amount", "amount.error",
                        "Сумма меньше 1 или не заполнено");
            } else if (createAccount.getAmount().compareTo(createAccount.getAccountPlanDTO().getMinAmount()) < 0) {
                errors.rejectValue("amount", "amount.error",
                        "Сумма вклада меньше допустимой");
            }

            if (createAccount.getPeriod() == null || createAccount.getPeriod() < 1) {
                errors.rejectValue("period", "period.error",
                        "Период меньше 1 или не заполнено");
            } else if (createAccount.getPeriod() < createAccount.getAccountPlanDTO().getMinPeriod()) {
                errors.rejectValue("period", "period.error",
                        "Срок вклада меньше допустимого");
            }

            if (createAccount.getCheckingAccountDTO() == null) {
                errors.rejectValue("checkingAccountDTO", "checkingAccountDTO.error",
                        "Не выбран счет для списания");
            }

        }
    }
}
