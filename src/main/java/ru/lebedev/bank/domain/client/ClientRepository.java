package ru.lebedev.bank.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.client.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
