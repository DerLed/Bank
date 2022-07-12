package ru.lebedev.bank.domain.checkingAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.transaction.TransactionStatus;
import ru.lebedev.bank.domain.checkingAccount.dto.CheckingAccountDTO;
import ru.lebedev.bank.domain.checkingAccount.mapper.CheckingAccountMapper;
import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.TransactionRepository;
import ru.lebedev.bank.exception.AccountTransferException;
import ru.lebedev.bank.exception.CloseDefaultAccountException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckingAccountServiceImpl implements CheckingAccountService {

    private static final String ACCOUNT_BY_ID_NOT_FOUND_MESSAGE = "Account with id %s is not found";

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
        //Проверяем есть ли у данного пользователя аккаунт по умолчанию, если нет то устанавливаем
        CheckingAccount checkingAccount = setDefaultAccount(checkingAccountMapper.toEntity(dto));
        CheckingAccount sCheckingAccount = checkingAccountRepository.saveAndFlush(checkingAccount);
        return checkingAccountMapper.toDTO(sCheckingAccount);
    }

    @Override
    public CheckingAccountDTO updateById(Long id, CheckingAccountDTO dto) {
        return checkingAccountRepository.findById(id)
                .map(account -> {
                    BeanUtils.copyProperties(checkingAccountMapper.toEntity(dto), account, "id");
                    return checkingAccountMapper.toDTO(checkingAccountRepository.save(account));
                })
                .orElseGet(() -> {
                    dto.setId(id);
                    return checkingAccountMapper.toDTO(checkingAccountRepository.save(checkingAccountMapper.toEntity(dto)));
                });
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CheckingAccount checkingAccount = checkingAccountRepository.findById(id).orElseThrow();
        if(checkingAccount.getIsDefault()){
            throw new CloseDefaultAccountException(id);
        }
        else checkingAccountRepository.closeById(id);
    }

    /**
     * В данном методе происходит пополнение счета, то есть по сути денежные средства беруться неоткуда
     * добавлено для демонстрации работы
     * @param accountId id пополняемого аккаунта
     * @param amount сумма
     */
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

    @Override
    public List<CheckingAccountDTO> findByClientId(Long clientId) {
        return checkingAccountRepository.findByClientId(clientId).stream()
                .map(checkingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CheckingAccountDTO> findByPhoneNumber(String phoneNumber) {
                return checkingAccountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber).stream()
                .map(checkingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    private CheckingAccount setDefaultAccount(CheckingAccount account){

            boolean notDefaultAccount = checkingAccountRepository
                    .findByClientIdAndIsDefaultTrueAndIsClosedFalse(account.getClient().getId()).isEmpty();
            account.setIsDefault(notDefaultAccount);
            return account;

    }

}
