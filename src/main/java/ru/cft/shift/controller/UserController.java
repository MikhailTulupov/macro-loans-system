package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.dto.UserDTO;
import ru.cft.shift.service.UserService;

import java.math.BigDecimal;

@Api
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(
            @RequestParam(name = "surname") String surname,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "patronymic") String patronymic,
            @RequestParam(name = "passport_series") String passportSeries,
            @RequestParam(name = "passport_number") String passportNumber
    ){
        try {
            return ResponseEntity.ok(userService.createUser(surname, name, patronymic, passportSeries, passportNumber));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<UserDTO> getCurrentUser(
            @RequestParam(name = "user_id") Long userId){
        try {
            return ResponseEntity.ok(userService.getCurrentUser(userId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/funds")
    public ResponseEntity<UserDTO> changeUserFunds(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "sum")BigDecimal sum
            ){
        try {
            return ResponseEntity.ok(userService.changeFunds(userId, sum));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


}
