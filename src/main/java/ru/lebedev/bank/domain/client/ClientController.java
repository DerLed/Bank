package ru.lebedev.bank.domain.client;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.lebedev.bank.domain.client.dto.ClientCreateReq;
import ru.lebedev.bank.domain.client.dto.ClientDTO;
import ru.lebedev.bank.exception.ClientNotFoundException;
import ru.lebedev.bank.exception.UserAlreadyExistException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> findAll() {
        return clientService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return clientService.findById(id).orElseThrow(()-> new ClientNotFoundException(id));
    }

    @PostMapping
    public ClientDTO create(@RequestBody @Valid ClientCreateReq client) throws UserAlreadyExistException {
        return clientService.newClient(client);
    }

    @PutMapping
    public ClientDTO update(@RequestBody @Valid ClientDTO client) {
        return clientService.save(client);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
