package com.openclassroom.paymybuddy.service.impl;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.BankAccountRepository;
import com.openclassroom.paymybuddy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void saveBankAccount(BankAccount bankAccount) {
    bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount findBankAccountByIban(String iban) {
        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByIban(iban);
        if (optionalBankAccount.isEmpty()) {
            return null;
        } else {
            return optionalBankAccount.get();
        }
    }

    @Override
    public BankAccount findBankAccountByUser(User user) {
        return bankAccountRepository.findOneByUser(user);
    }

    @Override
    public BankAccount findByUserId(Integer userId) {
        return bankAccountRepository.findByUserId(userId);
    }
}
