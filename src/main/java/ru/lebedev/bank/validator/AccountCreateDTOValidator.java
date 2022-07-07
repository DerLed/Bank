package ru.lebedev.bank.validator;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lebedev.bank.domain.account.dto.AccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;


import java.math.BigDecimal;

@Component
@NoArgsConstructor
public class AccountCreateDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        boolean a = AccountCreateDTO.class.equals(clazz);
        return a;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountCreateDTO createAccount = (AccountCreateDTO) target;

        if (createAccount.getAccountPlan() == null) {

            errors.rejectValue("accountPlan", "accountPlan.error", "Не выбран продукт");

        }

        if (createAccount.getAccountPlan() != null) {
            TypeAccount typeAccount = createAccount.getAccountPlan().getType();
            if (typeAccount.equals(TypeAccount.SAVING) || typeAccount.equals(TypeAccount.LOAN)) {

                if (createAccount.getAmount() == null ||
                        createAccount.getAmount().compareTo(BigDecimal.ONE) < 0) {
                    errors.rejectValue("amount", "amount.error", "Сумма меньше 1 или не заполнено");
                }

                if (createAccount.getPeriod() == null ||
                        createAccount.getPeriod() < 1) {
                    errors.rejectValue("period", "period.error", "Период меньше 1 или не заполнено");
                }

                if (createAccount.getSourceAccount() == null ) {
                    errors.rejectValue("sourceAccount", "sourceAccount.error", "Не выбран счет для открытия");
                }

            }
        }




    }
}
