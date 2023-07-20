package com.openclassroom.paymybuddy.service.impl;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.BankAccountRepository;
import com.openclassroom.paymybuddy.service.BankAccountService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserService userService;

    @Override
    public void saveBankAccount(BankAccount bankAccount) {
        if (bankAccount.getAmount() == null) {
            bankAccount.setAmount(0.0);
        }
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
    public BankAccount findByUserId(Integer idUser) {
        return bankAccountRepository.findByUserId(idUser);
    }

    @Transactional
    @Override
    public boolean debitAccount(double amount) {
        User user = userService.getLoggedUser();
        BankAccount bankAccount = bankAccountRepository.findByUserId(user.getId());
        if (bankAccount != null && bankAccount.getAmount() >= amount) {
            bankAccount.setAmount(bankAccount.getAmount() - amount);
            bankAccountRepository.save(bankAccount);
            user.setBalance(user.getBalance() + amount);
            userService.updateUser(user);
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public boolean creditAccount(double amount) {
        User user = userService.getLoggedUser();
        BankAccount bankAccount = bankAccountRepository.findByUserId(user.getId());
        if (bankAccount != null && user.getBalance() >= amount) {
            user.setBalance(user.getBalance() - amount);
            userService.updateUser(user);
            bankAccount.setAmount(bankAccount.getAmount() + amount);
            bankAccountRepository.save(bankAccount);
            return true;
        }
        return false;
    }

    public void updateBankAccount() {
        User user = userService.getLoggedUser();
        BankAccount bankAccount = bankAccountRepository.findByUserId(user.getId());

        if (bankAccount != null) {
            user.setBankAccount(bankAccount);
            userService.updateUser(user);
            bankAccountRepository.save(bankAccount);// Sauvegarder les modifications de l'utilisateur
        }
    }

}
