package com.openclassroom.paymybuddy.repository;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    Optional<BankAccount> findByIban(String Iban);

    BankAccount findOneByUser(User user);

    BankAccount findByUserId(Integer userId);
}
