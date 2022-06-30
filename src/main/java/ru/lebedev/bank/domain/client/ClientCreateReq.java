package ru.lebedev.bank.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lebedev.bank.domain.Role;
import ru.lebedev.bank.domain.Status;
import ru.lebedev.bank.domain.user.UserDTO;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCreateReq {

    @NotBlank(message = "Name incorrect")
    private String name;

    @NotBlank(message = "Surname incorrect")
    private String surname;

    @Email
    @NotBlank(message = "Email not be empty")
    private String email;

    @NotBlank(message = "Phone Number not be empty")
    @Min(value = 6, message = "Phone Number must be min 6 characters")
    private String phoneNumber;

    @NotBlank(message = "Password must be min 8 characters")
    @Min(value = 8)
    private String password;


}
