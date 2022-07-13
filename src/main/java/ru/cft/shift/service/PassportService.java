package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.entity.PassportEntity;
import ru.cft.shift.exception.IncorrectPassportException;
import ru.cft.shift.exception.PassportAlreadyRegisteredException;
import ru.cft.shift.exception.SmallAgeException;
import ru.cft.shift.repository.PassportRepository;
import ru.cft.shift.repository.UserRepository;
import ru.cft.shift.utils.PassportChecker;
import ru.cft.shift.utils.SecurityContextHelper;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PassportService {
    private final UserRepository userRepository;
    private final PassportRepository passportRepository;

    @Transactional
    public void updatePassportData(String series, String number)
            throws PassportAlreadyRegisteredException, SmallAgeException, IncorrectPassportException {
        checkIsPassportDataFree(series, number);

        PassportChecker.checkUserAge(series, number);
        PassportChecker.checkPassportDataExist(series, number);

        userRepository.findByEmail(SecurityContextHelper.email()).ifPresent(
                currentUser->currentUser
                        .setPassport(new PassportEntity()
                                .setSeries(series)
                                .setNumber(number)
                                .setId(currentUser.getId())
                                .setUser(currentUser)));
    }

    @Transactional
    public boolean isUserHasPassportData(){
        return passportRepository.existsByUserEmail(SecurityContextHelper.email());
    }

    private void checkIsPassportDataFree(String series, String number) throws PassportAlreadyRegisteredException {
        if(passportRepository.existsBySeriesAndNumber(series, number)){
            throw new PassportAlreadyRegisteredException();
        }
    }
}
