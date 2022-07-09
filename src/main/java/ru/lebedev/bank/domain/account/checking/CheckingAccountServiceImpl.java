package ru.lebedev.bank.domain.account.checking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.TransactionStatus;
import ru.lebedev.bank.domain.account.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.account.mapper.CheckingAccountMapper;
import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.TransactionRepository;
import ru.lebedev.bank.exception.AccountTransferException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckingAccountServiceImpl implements CheckingAccountService {

        private static final String ACCOUNT_BY_ID_NOT_FOUND_MESSAGE = "Account with id %s is not found";
    private static final String TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT =
            "Transfer amount higher than source account amount";
    private static final String ACCOUNT_BY_PHONE_NUMBER_NOT_FOUND_MESSAGE =
            "Account with phone number: %s is not found";
    private static final String ACCOUNT_BY_CARD_NUMBER_NOT_FOUND_MESSAGE = "Account with card number: %s is not found";

    private final CheckingAccountRepository checkingAccountRepository;
    private final CheckingAccountMapper checkingAccountMapper;
    private final TransactionRepository transactionRepository;


    @Override
    public List<CheckingAccountDTO> findAll() {
        return checkingAccountRepository.findAll().stream()
                .map(checkingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CheckingAccountDTO> findById(Long id) {
        return checkingAccountRepository.findByIdAndIsClosedFalse(id)
                .map(checkingAccountMapper::toDTO);
    }

    @Override
    public List<CheckingAccountDTO> findByClientLogin(String login) {
        return checkingAccountRepository.findByClientUserLoginAndIsClosedFalse(login).stream()
                .map(checkingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CheckingAccountDTO save(CheckingAccountDTO dto) {
        CheckingAccount checkingAccount = checkingAccountMapper.toEntity(dto);
        CheckingAccount sCheckingAccount = checkingAccountRepository.saveAndFlush(checkingAccount);
        return checkingAccountMapper.toDTO(sCheckingAccount);
    }

    @Override
    public CheckingAccountDTO updateById(Long id, CheckingAccountDTO dto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        checkingAccountRepository.closeById(id);
    }

    @Override
    @Transactional
    public void addMoney(Long accountId, BigDecimal amount) {
        Optional<CheckingAccount> refillAccount = checkingAccountRepository.findByIdAndIsClosedFalse(accountId);
        if (refillAccount.isEmpty()) {
            throw new AccountTransferException(String.format(ACCOUNT_BY_ID_NOT_FOUND_MESSAGE, accountId));
        }

        Transaction transaction = Transaction.builder()
                .targetAccount(refillAccount.get())
                .sourceAccount(refillAccount.get())
                .amount(amount)
                .status(TransactionStatus.DONE)
                .build();

        transactionRepository.saveAndFlush(transaction);
        refillAccount.get().setAmount(refillAccount.get().getAmount().add(amount));

    }

//    @Override
//    @Transactional(noRollbackFor = AccountTransferException.class)
//    public void transferMoneyByUserPhoneNumber(Long accountId, String phoneNumber, BigDecimal amount) {
//        List<CheckingAccount> accounts = checkingAccountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber);
//        if (accounts.isEmpty()) {
//            throw new AccountTransferException(String.format(ACCOUNT_BY_PHONE_NUMBER_NOT_FOUND_MESSAGE, phoneNumber));
//        }
//        CheckingAccount targetAccount = accounts.get(0);
//        transferAmount(amount, accountId, targetAccount);
//    }




}
