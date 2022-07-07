package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.model.LoanEntity;
import ru.cft.shift.repository.LoanRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    @Transactional
    public List<LoanDTO> getUserLoans(UUID userId){
        return loanRepository.findAllByUserId(userId).stream().map(LoanDTO::getFromEntity).collect(Collectors.toList());
    }
}
