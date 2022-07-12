package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.entity.UserEntity;
import ru.cft.shift.exception.EmailAlreadyRegisteredException;
import ru.cft.shift.exception.IncorrectPassportException;
import ru.cft.shift.exception.PassportAlreadyRegisteredException;
import ru.cft.shift.exception.SmallAgeException;
import ru.cft.shift.service.UserService;
import ru.cft.shift.utils.BcryptGenerator;
import ru.cft.shift.utils.SecurityContextHelper;

@Api
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sing-up")
    public ResponseEntity<UserDTO> register(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "patronymic") String patronymic,
            @RequestParam(name = "passportSeries", required = false) String passportSeries,
            @RequestParam(name = "passportNumber", required = false) String passportNumber
    ) throws
            EmailAlreadyRegisteredException,
            SmallAgeException,
            IncorrectPassportException,
            PassportAlreadyRegisteredException
    {
        return ResponseEntity.ok(
                userService.createUser(
                        email,
                        BcryptGenerator.passwordEncoder(password),
                        surname,
                        name,
                        patronymic,
                        passportSeries,
                        passportNumber));
    }

    @PostMapping("/sing-out")
    public void logout() {
        SecurityContextHelper.setNotAuthenticated();
    }

    @PostMapping("/sing-in")
    public ResponseEntity<UserDTO> login(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ){

        UserEntity user = userService.findUserByEmail(email);
        if(user == null){
            return ResponseEntity.badRequest().body(null);
        }

        String existingPassword = user.getPassword();

        if(!BcryptGenerator.passwordDecoder(password, existingPassword)){
            return ResponseEntity.badRequest().body(null);
        }

        SecurityContextHelper.setAuthenticated(new UsernamePasswordAuthenticationToken(email, password));

        UserDTO userDTO = userService.getCurrentUser();
        if(userDTO == null){
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(userDTO);
    }
}
