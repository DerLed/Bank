package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.exception.AccountTransferException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_BY_ID_NOT_FOUND_MESSAGE = "Account with id %s is not found";
    private static final String TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT =
            "Transfer amount higher than source account amount";

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

    @Override
    public Optional<AccountDTO> findById(Long id) {
        return accountRepository.findByIdAndIsClosedFalse(id)
                .map(accountMapper::toDTO);
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

    @Override
    public List<AccountDTO> findByClientLoginLoanAccounts(String login) {
        return accountRepository.findByClientUserLoginAndIsClosedFalseAndAccountPlan_Type(login, TypeAccount.LOAN).stream()
                .map(accountMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> findByClientLoginSavingAccounts(String login) {
        return accountRepository.findByClientUserLoginAndIsClosedFalseAndAccountPlan_Type(login, TypeAccount.SAVING).stream()
                .map(accountMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> findByClientLoginCheckingAccounts(String login) {
        return accountRepository.findByClientUserLoginAndIsClosedFalseAndAccountPlan_Type(login, TypeAccount.CHECKING).stream()
                .map(accountMapper::toDTO).collect(Collectors.toList());
    }

    public List<AccountDTO> findByPhoneNumber(String phoneNumber) {
        return accountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber).stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount) {
        List<Account> accounts = accountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber);

        Account targetAccount = accounts.get(0);

        transferAmount(amount, accountId, targetAccount);
    }

    @Override
    public void deleteById(Long id) {

    }

    private void transferAmount(BigDecimal amount, Long accountId, Account accountTarget) {
        Account accountSource = getAccount(accountId);

        if (accountSource.getAmount().compareTo(amount) < 0) {
           // transaction.setState(TransactionState.CANCELLED);
            throw new AccountTransferException(TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT);
        } else {
            //transaction.setState(TransactionState.DONE);
            accountSource.setAmount(accountSource.getAmount().subtract(amount));
            accountTarget.setAmount(accountTarget.getAmount().add(amount));
        }
    }

    private Account getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findByIdAndIsClosedFalse(accountId);
        return account.orElseThrow(()
                -> new AccountTransferException(String.format(ACCOUNT_BY_ID_NOT_FOUND_MESSAGE, accountId)));
    }


}
