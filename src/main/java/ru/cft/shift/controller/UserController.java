package ru.cft.shift.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDTO register(
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "patronymic") String patronymic,
            @RequestParam(name = "passport_series") String passportSeries,
            @RequestParam(name = "passport_number") String passportNumber
    ){
        return userService.createUser(surname, name, patronymic, passportSeries, passportNumber);
    }

    @GetMapping("/user")
    public UserDTO getCurrentUser(
            @RequestParam(name = "user_id") UUID userId){
        return userService.getCurrentUser(userId);
    }


}
