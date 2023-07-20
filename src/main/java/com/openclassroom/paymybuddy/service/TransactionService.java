package com.openclassroom.paymybuddy.service;
import com.openclassroom.paymybuddy.model.CreateTransactionResult;
import com.openclassroom.paymybuddy.model.Transaction;
import java.util.List;
public interface TransactionService {

    public CreateTransactionResult saveTransaction(Integer idReceiver, double amount);


    public List<Transaction> findTransactionByIdSender(Integer idUser);
}
