package ru.lebedev.bank.domain.account.saving;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.transaction.TransactionStatus;
import ru.lebedev.bank.domain.account.AccountService;
import ru.lebedev.bank.domain.account.checking.CheckingAccount;
import ru.lebedev.bank.domain.account.checking.CheckingAccountRepository;
import ru.lebedev.bank.domain.account.dto.SavingAccountCreateDTO;
import ru.lebedev.bank.domain.account.dto.SavingAccountDTO;
import ru.lebedev.bank.domain.account.mapper.SavingAccountMapper;
import ru.lebedev.bank.domain.transaction.Transaction;
import ru.lebedev.bank.domain.transaction.TransactionRepository;
import ru.lebedev.bank.domain.transaction.TransactionService;
import ru.lebedev.bank.exception.AccountTransferException;
import ru.lebedev.bank.utills.DepositCalc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingAccountServiceImpl implements SavingAccountService {

    private static final String TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT =
            "Transfer amount higher than source account amount";

    private final SavingAccountRepository savingAccountRepository;
    private final SavingAccountMapper savingAccountMapper;
    private final CheckingAccountRepository checkingAccountRepository;
    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public List<SavingAccountDTO> findAll() {
        return savingAccountRepository.findAll().stream()
                .map(savingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SavingAccountDTO> findById(Long id) {
        return savingAccountRepository.findByIdAndIsClosedFalse(id)
                .map(savingAccountMapper::toDTO);
    }

    @Override
    public List<SavingAccountDTO> findByClientLogin(String login) {
        return savingAccountRepository.findByClientUserLoginAndIsClosedFalse(login).stream()
                .map(savingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SavingAccountDTO create(SavingAccountCreateDTO savingAccountCreateDTO) {

        CheckingAccount checkingAccount = checkingAccountRepository.findById(savingAccountCreateDTO.getCheckingAccountDTO().getId()).orElseThrow();

        if (checkingAccount.getAmount().compareTo(savingAccountCreateDTO.getAmount()) < 0) {
            throw new AccountTransferException(TRANSFER_AMOUNT_HIGHER_THEN_ACCOUNT_AMOUNT);
        }

        checkingAccount.setAmount(checkingAccount.getAmount().subtract(savingAccountCreateDTO.getAmount()));

        SavingAccountDTO savingAccountDTO = SavingAccountDTO.builder()
                .accountPlanDTO(savingAccountCreateDTO.getAccountPlanDTO())
                .clientDTO(savingAccountCreateDTO.getClientDTO())
                .amount(savingAccountCreateDTO.getAmount())
                .period(savingAccountCreateDTO.getPeriod())
                .build();

        SavingAccount savedSavingAccount = savingAccountRepository.saveAndFlush(savingAccountMapper.toEntity(savingAccountDTO));

        Transaction transaction = Transaction.builder()
                .amount(savingAccountCreateDTO.getAmount())
                .sourceAccount(checkingAccount)
                .targetAccount(savedSavingAccount)
                .status(TransactionStatus.DONE)
                .build();

        transactionRepository.saveAndFlush(transaction);

        return savingAccountMapper.toDTO(savedSavingAccount);
    }

    @Override
    public List<SavingAccountDTO> findByClientId(Long clientId) {
        return savingAccountRepository.findByClientId(clientId).stream()
                .map(savingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SavingAccountDTO> findByPhoneNumber(String phoneNumber) {
        return savingAccountRepository.findByClientPhoneNumberAndIsClosedFalse(phoneNumber).stream()
                .map(savingAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SavingAccountDTO save(SavingAccountDTO dto) {
        SavingAccount savingAccount = savingAccountMapper.toEntity(dto);
        SavingAccount sSavingAccount = savingAccountRepository.saveAndFlush(savingAccount);
        return savingAccountMapper.toDTO(sSavingAccount);
    }

    @Override
    public SavingAccountDTO updateById(Long id, SavingAccountDTO dto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        SavingAccount closeAccount = savingAccountRepository.findById(id).orElseThrow();
        CheckingAccount defaultAccount = checkingAccountRepository.findByAccountIdDefaultAccount(id).orElseThrow();

        //При закрытии накопительного счета начисляем проценты на аккаунт по умолчанию

        closeAccount.setAmount(closeAccount.getAmount().add(DepositCalc.depositCalc(closeAccount)));

        accountService.transferMoney(closeAccount.getAmount(), closeAccount.getId(), defaultAccount);

        savingAccountRepository.closeById(id);

    }

}
