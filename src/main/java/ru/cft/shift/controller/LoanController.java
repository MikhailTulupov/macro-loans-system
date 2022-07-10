package ru.cft.shift.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.service.LoanService;
import ru.cft.shift.service.UserService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final UserService userService;

    @GetMapping("/user/all")
    public ResponseEntity<List<LoanDTO>> getUserLoans(
            @RequestParam(name = "user_id") Long userId
            ){
        try {
            return ResponseEntity.ok(loanService.getUserLoans(userId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/user/apply")
    public ResponseEntity<LoanDTO> applyForLoan(
            @RequestParam(name = "debt") BigDecimal debt,
            @RequestParam(name = "maturity")Instant maturity,
            @RequestParam(name = "rate") BigDecimal interestRate
            ){
        try{
            return ResponseEntity.ok(userService.addLoan(debt, maturity, interestRate));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<LoanDTO> payTheDebtOff(
            @PathVariable(name = "id") Long loanId,
            @RequestParam(name = "sum") BigDecimal sum
    ){
        try{
            return ResponseEntity.ok(loanService.payDebtOff(loanId, userService.getCurrentUser().getId(), sum));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getCurrentLoan(
            @PathVariable(name = "id") Long loanId
    ){
        try {
            return ResponseEntity.ok(loanService.getUserLoanById(loanId, userService.getCurrentUser().getId()));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
