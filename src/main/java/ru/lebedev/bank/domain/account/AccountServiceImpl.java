package ru.lebedev.bank.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.account.checking.CheckingAccount;
import ru.lebedev.bank.domain.account.dto.AccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.AccountDTO;
import ru.lebedev.bank.domain.account.mapper.AccountCreateMapper;
import ru.lebedev.bank.domain.account.mapper.AccountMapper;
import ru.lebedev.bank.domain.accountPlan.TypeAccount;
import ru.lebedev.bank.domain.client.Client;
import ru.lebedev.bank.domain.client.mapper.ClientMapper;
import ru.lebedev.bank.domain.loan.Loan;
import ru.lebedev.bank.domain.loan.LoanRepository;
import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.dto.TransactionDTO;
import ru.lebedev.bank.domain.transaction.mapper.TransactionMapper;
import ru.lebedev.bank.domain.transaction.TransactionService;
import ru.lebedev.bank.exception.AccountTransferException;
import ru.lebedev.bank.utills.DepositCalc;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
//
    private static final String ACCOUNT_BY_ID_NOT_FOUND_MESSAGE = "Account with id %s is not found";
    private static final String TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT =
            "Transfer amount higher than source account amount";
    private static final String ACCOUNT_BY_PHONE_NUMBER_NOT_FOUND_MESSAGE =
            "Account with phone number: %s is not found";
//    private static final String ACCOUNT_BY_CARD_NUMBER_NOT_FOUND_MESSAGE = "Account with card number: %s is not found";
//
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
//    private final AccountCreateMapper accountCreateMapper;
//    private final LoanRepository loanRepository;
//    private final ClientMapper clientMapper;
//



    /**
     * Метод для перевода денег с одного аккаунта на другой,
     * также производится запись в транзакции
     * @param amount сумма перевода
     * @param accountId id аккаунта с которого будут списаны средства
     * @param accountTarget аккаунт на который будут зачислены деньги
     */
    private void transferAmount(BigDecimal amount, Long accountId, Account accountTarget) {
        Account accountSource = getAccount(accountId);
        Transaction transaction = getTransaction(amount,accountSource, accountTarget);

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


//    public List<AccountDTO> findAll() {
//        return accountRepository.findAll().stream()
//                .map(accountMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        Account savedAccount = accountRepository.saveAndFlush(accountMapper.toEntity(accountDTO));
        return accountMapper.toDTO(savedAccount);
    }
//
//    @Override
//    @Transactional
//    public AccountDTO create(AccountCreateDTO accountCreateDTO) {
//
////        Account newAccount = Account.builder()
////                .client(clientMapper.toEntity(accountCreateDTO.getClient()))
////                .
////                .build();
//
//        //проверка, что CHECKING аккаунта на наличие дефолтного, если нет то устанавливается
//        Account checkedAccount = setDefaultAccount(accountCreateMapper.toEntity(accountCreateDTO));
//
//        Account savedAccount = accountRepository.saveAndFlush(checkedAccount);
//
//        //если счет накопительный переводим на него деньги с депозитного счета выбранного при оформлении
//        if(accountCreateDTO.getAccountPlan().getType().equals(TypeAccount.SAVING)) {
//            transferAmount(accountCreateDTO.getAmount(),
//                    accountCreateDTO.getSourceAccount().getId(),
//                    savedAccount);
//        }
//
////        if(accountCreateDTO.getAccountPlan().getType().equals(TypeAccount.LOAN)) {
////
////            Account linkedAccount = accountMapper.toEntity(accountCreateDTO.getSourceAccount());
////
////            Loan loan = loanRepository.saveAndFlush(new Loan());
////
////            loan.setAccount(savedAccount);
////            loan.setClient(savedAccount.getClient());
////            loan.setLinkedAccount(linkedAccount);
////            loan.setIsClosed(false);
////
////            transferAmount(accountCreateDTO.getAmount(),
////                    savedAccount.getId(), linkedAccount);
////            savedAccount.setAmount(accountCreateDTO.getAmount());
////        }
//
//        return accountMapper.toDTO(savedAccount);
//    }
//
    @Override
    public Optional<AccountDTO> findById(Long id) {
        return accountRepository.findByIdAndIsClosedFalse(id)
                .map(accountMapper::toDTO);
    }
//
//    public List<AccountDTO> findByClientId(Long clientId) {
//        return accountRepository.findByClientId(clientId).stream()
//                .map(accountMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<AccountDTO> findByClientLoginAndType(String login, TypeAccount type) {
//        return accountRepository.findByClientUserLoginAndIsClosedFalseAndAccountPlan_Type(login, type)
//                .stream().map(accountMapper::toDTO).collect(Collectors.toList());
//    }
//
    @Override
    public List<AccountDTO> findAllByClientLogin(String login) {
        return accountRepository.findByClientUserLoginAndIsClosedFalse(login).stream()
                .map(accountMapper::toDTO).collect(Collectors.toList());
    }
//
//    @Override
//    public List<TransactionDTO> getHistory(Long id) {
//        List<TransactionDTO> transactionsBySourceId = transactionService.findAllBySourceAccountId(id);
//        List<TransactionDTO> transactionsByTargetId = transactionService.findAllByTargetAccountId(id);
//        return Stream.concat(transactionsBySourceId.stream(), transactionsByTargetId.stream())
//                .sorted(Comparator.comparing(TransactionDTO::getDate))
//                .distinct().collect(Collectors.toList());
//    }
//
//    public List<AccountDTO> findByPhoneNumber(String phoneNumber) {
//        return accountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber).stream()
//                .map(accountMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<AccountDTO> findByCardNumber(String cardNumber) {
//        Optional<Account> account = accountRepository.findByCardNumber(cardNumber);
//        return account.map(accountMapper::toDTO);
//    }
//
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
//
//    @Override
//    @Transactional(noRollbackFor = AccountTransferException.class)
//    public void transferMoneyByCardNumber(Long accountId, String cardNumber, BigDecimal amount) {
//        Optional<Account> targetAccount = accountRepository.findByCardNumber(cardNumber);
//        if (targetAccount.isEmpty()) {
//            throw new AccountTransferException(String.format(ACCOUNT_BY_CARD_NUMBER_NOT_FOUND_MESSAGE, cardNumber));
//        }
//        transferAmount(amount, accountId, targetAccount.get());
//    }
//
//    @Override
//    @Transactional
//    public void addMoney(Long accountId, BigDecimal amount) {
//        Optional<Account> refillAccount = accountRepository.findByIdAndIsClosedFalse(accountId);
//        if (refillAccount.isEmpty()) {
//            throw new AccountTransferException(String.format(ACCOUNT_BY_ID_NOT_FOUND_MESSAGE, accountId));
//        }
//        addMoneyToAccount(amount, refillAccount.get());
//    }
//
//    /**
//     * Метод удаления аккаунта,
//     * сначала переводим деньги с закрываемого аккаунта на аккаунт по умолчанию,
//     * а затем закрываем его
//     * @param id id удаляемаого аккаунта
//     */
//    @Override
//    @Transactional(noRollbackFor = AccountTransferException.class)
//    public void close(Long id) {
//
//        Account closeAccount = accountRepository.findById(id).orElseThrow();
//        Account defaultAccount = accountRepository.findByAccountIdDefaultAccount(id).orElseThrow();
//
//        //При закрытии накопительного счета начисляем проценты
////        if(closeAccount.getAccountPlan().getType().equals(TypeAccount.SAVING)){
////            closeAccount.setAmount(closeAccount.getAmount().add(DepositCalc.depositCalc(closeAccount)));
////        }
//        transferAmount(closeAccount.getAmount(), closeAccount.getId(), defaultAccount);
//        accountRepository.closeById(id);
//    }
//
//    /**
//     * Метод для перевода денег с одного аккаунта на другой,
//     * также производится запись в транзакции
//     * @param amount сумма перевода
//     * @param accountId id аккаунта с которого будут списаны средства
//     * @param accountTarget аккаунт на который будут зачислены деньги
//     */
//    private void transferAmount(BigDecimal amount, Long accountId, Account accountTarget) {
//        Account accountSource = getAccount(accountId);
//        Transaction transaction = getTransaction(amount,accountSource, accountTarget);
//
//        if (accountSource.getAmount().compareTo(amount) < 0) {
//           transaction.setStatus(TransactionStatus.CANCELLED);
//            transactionService.save(transactionMapper.toDTO(transaction));
//            throw new AccountTransferException(TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT);
//        } else {
//            transaction.setStatus(TransactionStatus.DONE);
//            accountSource.setAmount(accountSource.getAmount().subtract(amount));
//            accountTarget.setAmount(accountTarget.getAmount().add(amount));
//            transactionService.save(transactionMapper.toDTO(transaction));
//        }
//    }
//
//    private void addMoneyToAccount(BigDecimal amount, Account refillAccount) {
//
//        Transaction transaction = getTransaction(amount, refillAccount, refillAccount);
//        transaction.setStatus(TransactionStatus.REFILL);
//        refillAccount.setAmount(refillAccount.getAmount().add(amount));
//        transactionService.save(transactionMapper.toDTO(transaction));
//
//    }
//
//
    private Account getAccount(Long accountId) {
        Optional<Account> account = accountRepository.findByIdAndIsClosedFalse(accountId);
        return account.orElseThrow(()
                -> new AccountTransferException(String.format(ACCOUNT_BY_ID_NOT_FOUND_MESSAGE, accountId)));
    }
//
//
//    private Account setDefaultAccount(Account account){
////        if(account.getAccountPlan().getType().equals(TypeAccount.CHECKING)){
////            boolean notDefaultAccount = accountRepository.findByClientIdAndIsDefaultTrueAndIsClosedFalse(account.getClient().getId()).isEmpty();
////            account.setIsDefault(notDefaultAccount);
////            return account;
////        }
//        return account;
//    }
//
    private Transaction getTransaction(BigDecimal amount, Account sourceAccount, Account targetAccount){
        return Transaction.builder()
                .amount(amount)
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .build();
    }

}
