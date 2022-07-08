package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.repository.LoanRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    @Transactional
    public List<LoanDTO> getUserLoans(Long userId){
        return loanRepository.findAllByUserId(userId).stream().map(LoanDTO::getFromEntity).collect(Collectors.toList());
    }

    @Transactional
    public LoanDTO payDebtOff(Long loanId, Long userId, BigDecimal sum){
        return LoanDTO.getFromEntity(loanRepository.setDebtSumByUserId(loanId, userId, sum));
    }

    @Transactional
    public LoanDTO getUserLoanById(Long loanId, Long userId){
        return LoanDTO.getFromEntity(loanRepository.getUserLoanById(loanId, userId).orElse(null));
    }

}
