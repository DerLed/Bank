package ru.lebedev.bank.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.helper.validGroup.Create;
import ru.lebedev.bank.domain.user.dto.UserDTO;
import ru.lebedev.bank.exception.UserNotFoundException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    @PostMapping
    public UserDTO create(@RequestBody @Validated(Create.class) UserDTO user) {
        return userService.save(user);
    }

    @PutMapping
    public UserDTO update(@RequestBody @Valid UserDTO user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
