package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.TransactionDTO;
import ru.lebedev.bank.domain.transaction.TransactionMapper;
import ru.lebedev.bank.domain.transaction.TransactionService;
import ru.lebedev.bank.exception.AccountTransferException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_BY_ID_NOT_FOUND_MESSAGE = "Account with id %s is not found";
    private static final String TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT =
            "Transfer amount higher than source account amount";
    private static final String ACCOUNT_BY_PHONE_NUMBER_NOT_FOUND_MESSAGE =
            "Account with phone number: %s is not found";

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

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

    @Override
    public List<TransactionDTO> getHistory(Long id) {
        List<TransactionDTO> transactionsBySourceId = transactionService.findAllBySourceAccountId(id);
        List<TransactionDTO> transactionsByTargetId = transactionService.findAllByTargetAccountId(id);
        return Stream.concat(transactionsBySourceId.stream(), transactionsByTargetId.stream())
                .sorted(Comparator.comparing(TransactionDTO::getDate))
                .collect(Collectors.toList());
    }

    public List<AccountDTO> findByPhoneNumber(String phoneNumber) {
        return accountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber).stream()
                .map(accountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> findByCardNumber(String cardNumber) {
        Optional<Account> account = accountRepository.findByCardNumber(cardNumber);
        return account.map(accountMapper::toDTO);
    }

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
    @Transactional
    public void close(Long id) {
        accountRepository.closeById(id);
    }


    private void transferAmount(BigDecimal amount, Long accountId, Account accountTarget) {
        Account accountSource = getAccount(accountId);


        //Transaction transaction = getTransaction(amount, accountSource, accountTarget);
        Transaction transaction = Transaction.builder()
                .amount(amount)
                .sourceAccount(accountSource)
                .targetAccount(accountTarget)
                .build();

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

    private Account getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findByIdAndIsClosedFalse(accountId);
        return account.orElseThrow(()
                -> new AccountTransferException(String.format(ACCOUNT_BY_ID_NOT_FOUND_MESSAGE, accountId)));
    }


    private Transaction getTransaction(BigDecimal amount, Account accountSource, Account accountTarget) {
        TransactionDTO transactionDTO = transactionService.create(TransactionDTO.builder()
                .sourceAccount(accountMapper.toDTO(accountSource))
                .targetAccount(accountMapper.toDTO(accountTarget))
                .amount(amount)
                .status(TransactionStatus.NEW)
                .build());

        return transactionMapper.toEntity(transactionDTO);
    }

}
