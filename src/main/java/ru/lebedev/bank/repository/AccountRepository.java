package ru.lebedev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClientId(Long ClientId);
    List<Account> findByClientPhone(String phoneNumber);
}
