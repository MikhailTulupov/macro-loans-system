package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.model.LoanEntity;
import ru.cft.shift.model.UserEntity;
import ru.cft.shift.repository.UserRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDTO createUser(
            String surname,
            String name,
            String patronymic,
            String passport_series,
            String passport_number){

        UserEntity user = userRepository.save(new UserEntity(surname, name, patronymic, passport_series, passport_number));
        return UserDTO.getFromEntity(user);
    }

    @Transactional
    public UserDTO getCurrentUser(Long userId){
        return UserDTO.getFromEntity(userRepository.findById(userId).orElse(null));
    }

    @Transactional
    public LoanDTO addLoan(Long userId, BigDecimal debt, Instant maturity, BigDecimal interestRate){
        LoanEntity loan = new LoanEntity()
                .setDebt(debt)
                .setMaturity(maturity)
                .setInterestRate(interestRate)
                .setDateOfReceive(Instant.now());

        userRepository.findById(userId).ifPresent(currentUser -> currentUser.getLoans().add(loan));
        return LoanDTO.getFromEntity(loan);
    }

    @Transactional
    public UserDTO changeFunds(Long userId, BigDecimal sum){
        userRepository.findById(userId).ifPresent(currentUser -> currentUser.setFunds(currentUser.getFunds().add(sum)));
        return UserDTO.getFromEntity(userRepository.findById(userId).orElse(null));
    }
}
