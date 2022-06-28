package ru.lebedev.bank.domain.user;

import lombok.*;
import ru.lebedev.bank.domain.Role;
import ru.lebedev.bank.domain.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usr")

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name incorrect")
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotNull
    @NotBlank
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotNull(message = "Password must be between 4 to 15 characters")
    @Size(min = 4, max = 15)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;


}
