package ru.lebedev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.entity.Client;
import ru.lebedev.bank.domain.entity.Person;

public interface ClientRepo extends JpaRepository<Client, Long> {
}

