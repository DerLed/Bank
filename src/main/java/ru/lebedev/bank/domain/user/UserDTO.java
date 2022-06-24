package ru.lebedev.bank.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lebedev.bank.domain.Role;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String regInfo;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
    private Boolean isActive;

}
