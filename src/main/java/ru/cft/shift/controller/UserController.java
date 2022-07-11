package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.exception.EmailAlreadyRegisteredException;
import ru.cft.shift.exception.IncorrectPassportException;
import ru.cft.shift.exception.PassportAlreadyRegisteredException;
import ru.cft.shift.exception.SmallAgeException;
import ru.cft.shift.service.UserService;
import ru.cft.shift.utils.SecurityContextHelper;

@Api
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "patronymic") String patronymic,
            @RequestParam(name = "passportSeries") String passportSeries,
            @RequestParam(name = "passportNumber") String passportNumber
    ) throws
            EmailAlreadyRegisteredException,
            SmallAgeException,
            IncorrectPassportException,
            PassportAlreadyRegisteredException
    {
            return ResponseEntity.ok(
                    userService.createUser(
                            email,
                            password,
                            surname,
                            name,
                            patronymic,
                            passportSeries,
                            passportNumber));
    }

    @GetMapping("/account")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/logout")
    public void logout() {
        SecurityContextHelper.setNotAuthenticated();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(){
        if(!userService.deleteUser()){
            return new ResponseEntity<>("Failed to delete user", HttpStatus.BAD_REQUEST);
        }

        SecurityContextHelper.setNotAuthenticated();
        return ResponseEntity.ok("Deleted");
    }
}
