package ru.lebedev.bank.domain.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCreateReq {

    @NotBlank(message = "Поле имя не может быть пустым")
    private String name;

    @NotBlank(message = "Поле фамилия не может быть пустым")
    private String surname;

    @Email
    @NotBlank(message = "Поле Email не может быть пустым")
    private String email;


    @Min(value = 6, message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @Min(value = 8, message = "Минимум 8 символов")
    private String password;


}
