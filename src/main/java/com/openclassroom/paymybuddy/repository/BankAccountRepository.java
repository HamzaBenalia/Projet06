package com.openclassroom.paymybuddy.repository;

import com.openclassroom.paymybuddy.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

}
