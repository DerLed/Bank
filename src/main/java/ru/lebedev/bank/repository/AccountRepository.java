package ru.lebedev.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
