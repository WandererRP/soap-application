package com.roland.soap.services;

import com.roland.soap.models.Account;
import com.roland.soap.repositories.AccountRepository;
import com.roland.soap.util.AccountNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Roland Pilpani 29.12.2022
 */

@Component
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account findById(long id){
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
