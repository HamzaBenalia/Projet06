package com.openclassroom.paymybuddy.service;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;

public interface BankAccountService {

    void saveBankAccount(BankAccount bankAccount);


    BankAccount findBankAccountByIban(String iban);

    BankAccount findBankAccountByUser(User user);

    BankAccount findByUserId(Integer userId);

    boolean debitAccount(double amount);

    boolean creditAccount(double amount);

    void updateBankAccount();
}
