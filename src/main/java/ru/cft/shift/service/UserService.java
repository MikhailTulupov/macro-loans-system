package ru.cft.shift.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.model.UserEntity;
import ru.cft.shift.repository.UserRepository;

import javax.transaction.Transactional;
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

    public UserDTO getCurrentUser(UUID userId){
        return UserDTO.getFromEntity(userRepository.findById(userId).orElse(null));
    }
}
