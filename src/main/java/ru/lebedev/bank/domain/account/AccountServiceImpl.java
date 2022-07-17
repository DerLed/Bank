package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.transaction.TransactionStatus;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.account.mapper.AccountMapper;

import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.mapper.TransactionMapper;
import ru.lebedev.bank.domain.transaction.TransactionService;
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
    private static final String ACCOUNT_BY_PHONE_NUMBER_NOT_FOUND_MESSAGE =
            "Account with phone number: %s is not found";
    private static final String ACCOUNT_BY_CARD_NUMBER_NOT_FOUND_MESSAGE = "Account with card number: %s is not found";

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;


    /**
     * Метод для перевода денег с одного аккаунта на другой,
     * также производится запись в транзакции
     *
     * @param amount        сумма перевода
     * @param accountId     id аккаунта с которого будут списаны средства
     * @param accountTarget аккаунт на который будут зачислены деньги
     */
    private void transferAmount(BigDecimal amount, Long accountId, Account accountTarget) {
        Account accountSource = getAccount(accountId);
        Transaction transaction = getTransaction(amount, accountSource, accountTarget);

        if (accountSource.getAmount().compareTo(amount) < 0) {
            transaction.setStatus(TransactionStatus.CANCELLED);
            transactionService.save(transactionMapper.toDTO(transaction));
            throw new AccountTransferException(TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT);
        } else {
            transaction.setStatus(TransactionStatus.DONE);
            accountSource.setAmount(accountSource.getAmount().subtract(amount));
            accountTarget.setAmount(accountTarget.getAmount().add(amount));
            transactionService.save(transactionMapper.toDTO(transaction));
        }
    }

    @Override
    @Transactional
    public AccountDTO save( AccountDTO accountDTO) {
        Account savedAccount = accountRepository.saveAndFlush(accountMapper.toEntity(accountDTO));
        return accountMapper.toDTO(savedAccount);
    }

    @Override
    @Transactional
    public AccountDTO updateById(Long id, AccountDTO dto) {
        return accountRepository.findById(id)
                .map(account -> {
                    BeanUtils.copyProperties(accountMapper.toEntity(dto), account, "id");
                    return accountMapper.toDTO(accountRepository.save(account));
                })
                .orElseGet(() -> {
                    dto.setId(id);
                    return accountMapper.toDTO(accountRepository.save(accountMapper.toEntity(dto)));
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(accountMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> findById(Long id) {
        return accountRepository.findByIdAndIsClosedFalse(id)
                .map(accountMapper::toDTO);
    }

    @Override
    public List<AccountDTO> findAllByClientLogin(String login) {
        return accountRepository.findByClientUserLoginAndIsClosedFalse(login).stream()
                .map(accountMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(noRollbackFor = AccountTransferException.class)
    public void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount) {
        List<Account> accounts = accountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber);
        if (accounts.isEmpty()) {
            throw new AccountTransferException(String.format(ACCOUNT_BY_PHONE_NUMBER_NOT_FOUND_MESSAGE, phoneNumber));
        }
        Account targetAccount = accounts.get(0);
        transferAmount(amount, accountId, targetAccount);
    }

    @Override
    @Transactional(noRollbackFor = AccountTransferException.class)
    public void transferMoney(BigDecimal amount, Long accountId, Account accountTarget) {
        transferAmount(amount, accountId, accountTarget);
    }

    @Override
    @Transactional(noRollbackFor = AccountTransferException.class)
    public void transferMoneyByCardNumber(Long accountId, String cardNumber, BigDecimal amount) {
        Optional<Account> targetAccount = accountRepository.findByCardNumber(cardNumber);
        if (targetAccount.isEmpty()) {
            throw new AccountTransferException(String.format(ACCOUNT_BY_CARD_NUMBER_NOT_FOUND_MESSAGE, cardNumber));
        }
        transferAmount(amount, accountId, targetAccount.get());
    }

    private Account getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findByIdAndIsClosedFalse(accountId);
        return account.orElseThrow(()
                -> new AccountTransferException(String.format(ACCOUNT_BY_ID_NOT_FOUND_MESSAGE, accountId)));
    }

    private Transaction getTransaction(BigDecimal amount, Account sourceAccount, Account targetAccount) {
        return Transaction.builder()
                .amount(amount)
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .build();
    }

}
