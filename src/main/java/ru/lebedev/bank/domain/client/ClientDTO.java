package ru.lebedev.bank.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lebedev.bank.domain.user.User;
import ru.lebedev.bank.domain.user.UserDTO;

@Data
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String email;
    private String phoneNumber;
    private UserDTO userDTO;
}
