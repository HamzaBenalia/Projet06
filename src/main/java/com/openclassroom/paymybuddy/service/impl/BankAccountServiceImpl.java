package com.openclassroom.paymybuddy.service.impl;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.repository.BankAccountRepository;
import com.openclassroom.paymybuddy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void saveBankAccount(BankAccount bankAccount) {
    bankAccountRepository.save(bankAccount);
    }
}
