package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lebedev.bank.domain.accountPlan.dto.AccountPlanDTO;
import ru.lebedev.bank.domain.accountPlan.mapper.AccountPlanMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountPlanServiceImpl implements AccountPlanService{

    private final AccountPlanRepository accountPlanRepository;
    private final AccountPlanMapper accountPlanMapper;


    @Override
    public List<AccountPlanDTO> findAll() {
        return accountPlanRepository.findAll().stream()
                .map(accountPlanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountPlanDTO save(AccountPlanDTO accountPlanDTO) {
        AccountPlan accountPlan = accountPlanMapper.toEntity(accountPlanDTO);
        accountPlanRepository.saveAndFlush(accountPlan);
        return accountPlanMapper.toDTO(accountPlan);
    }

    @Override
    @Transactional
    public AccountPlanDTO updateById(Long id, AccountPlanDTO accountPlanDTO) {
        return accountPlanRepository.findById(id)
                .map(account -> {
                    BeanUtils.copyProperties(accountPlanMapper.toEntity(accountPlanDTO), account, "id");
                    return accountPlanMapper.toDTO(accountPlanRepository.save(account));
                })
                .orElseGet(() -> {
                    accountPlanDTO.setId(id);
                    return accountPlanMapper.toDTO(accountPlanRepository.save(accountPlanMapper.toEntity(accountPlanDTO)));
                });
    }

    @Override
    public Optional<AccountPlanDTO> findById(Long id) {
        return accountPlanRepository.findById(id)
                .map(accountPlanMapper::toDTO);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        accountPlanRepository.deleteById(id);
    }

}
