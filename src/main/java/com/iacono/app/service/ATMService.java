package com.iacono.app.service;

import com.iacono.app.model.Account;
import com.iacono.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ATMService {

    private final AccountRepository accountRepository;

    public Account login(Long id, String pin) throws Exception {
        Optional<Account> account = accountRepository.findByIdAndPin(id, pin);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new Exception("Account non trovato o PIN errato ");
        }
    }
    public BigDecimal checkBalance(Long accountId) throws Exception{
        Account account = accountRepository.findById(accountId)
                .orElseThrow((() -> new Exception("Account non trovato")));
        return account.getBalance();

    }

    public Account deposit (Long accountId, BigDecimal amount) throws Exception{
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new Exception("Account non trovato"));
        return accountRepository.save(account);
    }

    public Account withdraw (Long accountId, BigDecimal amount) throws Exception{
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new Exception("Account non trovato"));
        if (account.getBalance().compareTo(amount) >=0) {
            account.setBalance(account.getBalance().subtract(amount));
            return accountRepository.save(account);
        } else{
            throw new Exception("Saldo insufficiente");
        }
    }
}
