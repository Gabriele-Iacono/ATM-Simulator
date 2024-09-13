package com.iacono.app.repository;

import com.iacono.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository <Account, Long> {
    Optional<Account> findByIdAndPin(Long id, String pin);
}
