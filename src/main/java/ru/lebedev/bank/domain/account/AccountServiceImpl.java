package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepository.saveAndFlush(account);
        return accountMapper.toDTO(savedAccount);
    }

    public List<AccountDTO> findByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId).stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> findByClientLogin(String login) {
        return accountRepository.findByClientUserLogin(login).stream()
                .map(accountMapper::toDTO).collect(Collectors.toList());
    }

    public List<AccountDTO> findByPhoneNumber(String phoneNumber) {
        return accountRepository.findByClientPhoneNumber(phoneNumber).stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {

    }

}
