package ru.lebedev.bank.domain.accountPlan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<AccountPlanDTO> findByType(TypeAccount typeAccount) {
        return accountPlanRepository.findByTypeEquals(typeAccount).stream()
                .map(accountPlanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountPlanDTO save(AccountPlanDTO accountPlanDTO) {
        AccountPlan accountPlan = accountPlanMapper.toEntity(accountPlanDTO);
        accountPlanRepository.saveAndFlush(accountPlan);
        return accountPlanMapper.toDTO(accountPlan);
    }

    @Override
    public Optional<AccountPlanDTO> findById(Long id) {
        return accountPlanRepository.findById(id)
                .map(accountPlanMapper::toDTO);
    }

    @Override
    public void delete(AccountPlanDTO accountPlanDTO) {
        AccountPlan accountPlan = accountPlanMapper.toEntity(accountPlanDTO);
        accountPlanRepository.delete(accountPlan);
    }

    @Override
    public void deleteById(Long id) {
        accountPlanRepository.deleteById(id);
    }


}
