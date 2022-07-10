package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.entity.BalanceEntity;
import ru.cft.shift.entity.UserEntity;
import ru.cft.shift.repository.BalanceRepository;
import ru.cft.shift.repository.UserRepository;
import ru.cft.shift.utils.SecurityContextHelper;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;

    @Transactional
    public BigDecimal changeUserBalance(BigDecimal sum){
        BalanceEntity balance = balanceRepository.findByUserEmail(SecurityContextHelper.email()).orElse(null);
        if(balance == null){
            return null;
        }

        balance.setFunds(balance.getFunds().add(sum));
        return balance.getFunds();
    }
}
