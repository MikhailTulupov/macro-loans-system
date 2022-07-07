package ru.cft.shift.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.shift.dto.LoanDTO;
import ru.cft.shift.service.LoanService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/user")
    public ResponseEntity<List<LoanDTO>> getUserLoans(
            @RequestParam(name = "user_id") UUID userId
            ){
        try {
            return ResponseEntity.ok(loanService.getUserLoans(userId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
