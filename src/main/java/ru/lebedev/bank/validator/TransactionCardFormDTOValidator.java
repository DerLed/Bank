package ru.lebedev.bank.validator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lebedev.bank.domain.card.CardService;
import ru.lebedev.bank.domain.card.dto.TransactionCardFormDTO;
import ru.lebedev.bank.domain.savingAccount.dto.SavingAccountCreateDTO;

@Component
@RequiredArgsConstructor
public class TransactionCardFormDTOValidator implements Validator {

    private final CardService cardService;


    @Override
    public boolean supports(Class<?> clazz) {
        return TransactionCardFormDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransactionCardFormDTO cardFormDTO = (TransactionCardFormDTO) target;

        if (cardFormDTO.getCardNumber() == null || cardFormDTO.getCardNumber().isBlank()){
            errors.rejectValue("cardNumber", "cardNumber.error", "Номер карты не может быть пустым");
        }
        else if (cardFormDTO.getCardNumber().length() < 16){
            errors.rejectValue("cardNumber", "cardNumber.error", "Введен не корректный номер карты");
        }
        else if (!cardService.existsByCardNumber(cardFormDTO.getCardNumber())) {
            errors.rejectValue("cardNumber", "cardNumber.error", "Данной карты нет в системе");
        }

        if (cardFormDTO.getCardDTO() == null){
            errors.rejectValue("cardDTO", "cardDTO.error", "Не выбрана карта списания");
        }

        if (cardFormDTO.getAmount() == null){
            errors.rejectValue("amount", "amount.error", "Сумма перевода не введена");
        }
        else if (cardFormDTO.getCardDTO() == null &&
                cardFormDTO.getAmount().compareTo(cardFormDTO.getCardDTO().getAccountDTO().getAmount()) > 0){
            errors.rejectValue("amount", "amount.error", "На данной карте не достаточно средств");
        }
    }
}
