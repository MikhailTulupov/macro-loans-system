package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.exception.IncorrectPassportException;
import ru.cft.shift.exception.PassportAlreadyRegisteredException;
import ru.cft.shift.exception.SmallAgeException;
import ru.cft.shift.service.PassportService;

@Api
@RestController
@RequestMapping("/api/passport")
@RequiredArgsConstructor
public class PassportController {
    private final PassportService passportService;

    @PostMapping("/user")
    public void setUserPassportData(
            @RequestParam(name = "series") String series,
            @RequestParam(name = "number") String number
    ) throws IncorrectPassportException, PassportAlreadyRegisteredException, SmallAgeException {
        passportService.setPassportData(series, number);
    }


}
