package ru.lebedev.bank.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.client.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPhoneNumber(String phoneNumber);
    Optional<Client> findByEmail(String email);
    Optional<Client> findByUserLogin(String login);
}
