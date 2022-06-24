package ru.lebedev.bank.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lebedev.bank.domain.user.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

}

