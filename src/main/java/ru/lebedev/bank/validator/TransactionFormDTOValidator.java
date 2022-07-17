package ru.lebedev.bank.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lebedev.bank.domain.card.CardService;
import ru.lebedev.bank.domain.checkingAccount.CheckingAccountService;
import ru.lebedev.bank.domain.checkingAccount.dto.TransactionFormDTO;
import ru.lebedev.bank.domain.client.ClientService;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TransactionFormDTOValidator implements Validator {

    private final CheckingAccountService checkingAccountService;


    @Override
    public boolean supports(Class<?> clazz) {
        return TransactionFormDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransactionFormDTO transactionFormDTO = (TransactionFormDTO) target;

        if (transactionFormDTO.getPhoneNumber() == null ||
                transactionFormDTO.getPhoneNumber().length() < 6) {
            errors.rejectValue("phoneNumber", "phoneNumber.error",
                    "Номер телефона введен некорректно");
        }
        else if(checkingAccountService.findByPhoneNumber(transactionFormDTO.getPhoneNumber()).isEmpty()){
            errors.rejectValue("phoneNumber", "phoneNumber.error",
                    "Данного номера нет в системе или нет привязанных счетов");
        }

        if (transactionFormDTO.getAccount() == null){
            errors.rejectValue("account", "account.error",
                    "Счет списания не выбран");
        }

        if(transactionFormDTO.getAmount() == null || transactionFormDTO.getAmount().compareTo(BigDecimal.ZERO) < 0){
            errors.rejectValue("amount", "amount.error",
                    "Сумма перевода введена не корректно");
        }
        else if (transactionFormDTO.getAccount() != null &&
                transactionFormDTO.getAmount().compareTo(transactionFormDTO.getAccount().getAmount()) > 0){
            errors.rejectValue("amount", "amount.error",
                    "На данном счете не достаточно средств");
        }
    }
}
