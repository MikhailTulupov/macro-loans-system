package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.exception.EmailAlreadyRegisteredException;
import ru.cft.shift.service.UserService;
import ru.cft.shift.utils.SecurityContextHelper;

import java.math.BigDecimal;

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
            @RequestParam(name = "patronymic") String patronymic
    )throws EmailAlreadyRegisteredException {
            return ResponseEntity.ok(userService.createUser(email, password, surname, name, patronymic));
    }

    @GetMapping("/account")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/logout")
    public void logout() {
        SecurityContextHelper.setNotAuthenticated();
    }


}
