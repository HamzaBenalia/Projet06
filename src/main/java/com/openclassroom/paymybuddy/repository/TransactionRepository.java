package com.openclassroom.paymybuddy.repository;
import com.openclassroom.paymybuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTransactionByIdUserSender(Integer idUserSender);

    List<Transaction> findTransactionByIdUserReceiver(Integer idUserReceiver);
}
