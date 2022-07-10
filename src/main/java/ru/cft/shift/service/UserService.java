package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.entity.LoanEntity;
import ru.cft.shift.entity.UserEntity;
import ru.cft.shift.exception.EmailAlreadyRegisteredException;
import ru.cft.shift.repository.UserRepository;
import ru.cft.shift.utils.SecurityContextHelper;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDTO createUser(
            String email,
            String password,
            String surname,
            String name,
            String patronymic) throws EmailAlreadyRegisteredException{
        checkEmailIsFree(email);
        UserEntity user = userRepository.save(new UserEntity(email, password, surname, name, patronymic));
        return UserDTO.getFromEntity(user);
    }

    @Transactional
    public UserDTO getCurrentUser(){
        return UserDTO.getFromEntity(userRepository.findByEmail(SecurityContextHelper.email()).orElse(null));
    }

    @Transactional
    public LoanDTO addLoan(BigDecimal debt, Instant maturity, BigDecimal interestRate){
        LoanEntity loan = new LoanEntity()
                .setDebt(debt)
                .setMaturity(maturity)
                .setInterestRate(interestRate)
                .setDateOfReceive(Instant.now());

        userRepository.findByEmail(SecurityContextHelper.email()).ifPresent(currentUser -> currentUser.getLoans().add(loan));
        return LoanDTO.getFromEntity(loan);
    }

    private void checkEmailIsFree(String email) throws EmailAlreadyRegisteredException {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    @Transactional
    public Boolean deleteUser(){
        if(!userRepository.existsByEmail(SecurityContextHelper.email())){
            return false;
        }

        userRepository.deleteByEmail(SecurityContextHelper.email());
        return true;
    }
}
