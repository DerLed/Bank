package ru.lebedev.bank.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lebedev.bank.domain.account.dto.AccountCardTransferFormDTO;
import ru.lebedev.bank.domain.checkingAccount.CheckingAccountService;
import ru.lebedev.bank.domain.checkingAccount.dto.TransactionFormDTO;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AccountCardTransferFormDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountCardTransferFormDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountCardTransferFormDTO accountCardTransferFormDTO = (AccountCardTransferFormDTO) target;
        if (accountCardTransferFormDTO.getCardDTO() == null){
            errors.rejectValue("cardDTO", "cardDTO.error",
                    "Карта зачисления не выбрана");
        }
        if (accountCardTransferFormDTO.getAccountDTO() == null){
            errors.rejectValue("accountDTO", "accountDTO.error",
                    "Счет списания не выбран");
        }
        if(accountCardTransferFormDTO.getAmount() == null || accountCardTransferFormDTO.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("amount", "amount.error",
                    "Сумма перевода введена не корректно");
        }
        else if (accountCardTransferFormDTO.getAccountDTO() != null &&
                accountCardTransferFormDTO.getAmount().compareTo(accountCardTransferFormDTO.getAccountDTO().getAmount()) > 0){
            errors.rejectValue("amount", "amount.error",
                    "На данном счете не достаточно средств");
        }
    }
}
