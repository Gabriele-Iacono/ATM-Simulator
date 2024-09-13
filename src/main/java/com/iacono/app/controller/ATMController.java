package com.iacono.app.controller;

import com.iacono.app.model.Account;
import com.iacono.app.service.ATMService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/atm")
@RequiredArgsConstructor
public class ATMController {
    @Autowired
    private final ATMService atmService;

    @PostMapping("/login")
    public Account login(@RequestParam Long id, @RequestParam String pin) throws Exception {
        return atmService.login(id, pin);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> checkBalance(@RequestParam Long accountId) throws Exception {
        try {
            BigDecimal balance = atmService.checkBalance(accountId);
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @PostMapping("/deposit")
    public Account deposit (@RequestParam Long accountId, @RequestParam BigDecimal amount) throws Exception{
        return atmService.deposit(accountId, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw (@RequestParam Long accountId, @RequestParam BigDecimal amount) throws Exception{
        return atmService.withdraw(accountId, amount);
    }
}
