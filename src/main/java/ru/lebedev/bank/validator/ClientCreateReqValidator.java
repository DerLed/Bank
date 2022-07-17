package ru.lebedev.bank.validator;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lebedev.bank.domain.client.ClientService;
import ru.lebedev.bank.domain.client.dto.ClientCreateReq;
import ru.lebedev.bank.domain.savingAccount.dto.SavingAccountCreateDTO;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ClientCreateReqValidator implements Validator {

    private final ClientService clientService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientCreateReq.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClientCreateReq clientCreateReq = (ClientCreateReq) target;

        if (clientService.findByPhoneNumber(clientCreateReq.getPhoneNumber()).isPresent()) {
            errors.rejectValue("phoneNumber", "phoneNumber.error",
                    "Данный номер уже зарегестрирован в системе");
        }


    }
}
