package ru.lebedev.bank.domain.account.dto;

import lombok.*;
import ru.lebedev.bank.domain.client.Client;
import ru.lebedev.bank.domain.client.dto.ClientDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckingAccountDTO {

    private Long id;

    private BigDecimal amount;

    private LocalDateTime dateOpened;

    private Boolean isClosed;

    private String accountNumber;

    private ClientDTO clientDTO;

    private Boolean isDefault;
}
