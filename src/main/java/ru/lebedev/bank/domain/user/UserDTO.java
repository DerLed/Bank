package ru.lebedev.bank.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.Role;
import ru.lebedev.bank.domain.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private Status status;
    private Role role;
}
