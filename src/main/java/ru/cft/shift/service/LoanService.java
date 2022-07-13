package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.entity.UserEntity;
import ru.cft.shift.exception.UserNotFoundException;
import ru.cft.shift.repository.LoanRepository;
import ru.cft.shift.repository.UserRepository;
import ru.cft.shift.utils.SecurityContextHelper;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    private final UserRepository userRepository;

    @Transactional
    public List<LoanDTO> getUserLoans() throws UserNotFoundException {
        UserEntity user = userRepository.findByEmail(SecurityContextHelper.email()).orElse(null);

        if(user == null){
            throw new UserNotFoundException();
        }

        return loanRepository.findAllByUserId(user.getId()).stream().map(LoanDTO::getFromEntity).collect(Collectors.toList());
    }

    @Transactional
    public LoanDTO payDebtOff(Long loanId, BigDecimal sum) throws UserNotFoundException {
        UserEntity user = userRepository.findByEmail(SecurityContextHelper.email()).orElse(null);

        if(user == null){
            throw new UserNotFoundException();
        }

        return LoanDTO.getFromEntity(loanRepository.setDebtSumByUserId(loanId, user.getId(), sum));
    }

    @Transactional
    public LoanDTO getUserLoanById(Long loanId, Long userId){
        return LoanDTO.getFromEntity(loanRepository.getUserLoanById(loanId, userId).orElse(null));
    }

    @Transactional
    public Boolean deleteLoanById(Long loanId){
        if(!loanRepository.existsById(loanId)){
            return false;
        }

        loanRepository.deleteById(loanId);
        return true;
    }

}
