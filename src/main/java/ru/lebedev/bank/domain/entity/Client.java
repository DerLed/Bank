package ru.lebedev.bank.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "client")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_gen")
    @SequenceGenerator(name = "client_gen", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne
    private Person person;

}
