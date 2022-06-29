package ru.lebedev.bank.domain.client;

import ru.lebedev.bank.domain.user.UserDTO;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ClientCreateReq {

    @Email
    @NotBlank(message = "Email not be empty")
    private String email;

    @NotBlank(message = "Phone Number not be empty")
    @Min(value = 6, message = "Phone Number must be min 6 characters")
    private String phoneNumber;

    @NotBlank(message = "Name incorrect")
    @Min(value = 2)
    private String name;

    @NotBlank(message = "Surname incorrect")
    @Min(value = 2)
    private String surname;

    @NotBlank(message = "Password must be min 8 characters")
    @Min(value = 8)
    private String password;

}
