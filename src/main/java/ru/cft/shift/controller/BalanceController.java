package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.shift.service.BalanceService;

import java.math.BigDecimal;

@Api
@RestController
@RequestMapping("/api/balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    @PutMapping("/user/change")
    public ResponseEntity<BigDecimal> changeUserBalance(
            @RequestParam(name = "sum") BigDecimal sum
    ){
        return ResponseEntity.ok(balanceService.changeUserBalance(sum));
    }
}
