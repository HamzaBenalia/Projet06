package com.openclassroom.paymybuddy.service.impl;

import com.openclassroom.paymybuddy.model.CreateTransactionResult;
import com.openclassroom.paymybuddy.model.Transaction;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.TransactionRepository;
import com.openclassroom.paymybuddy.service.RelationsService;
import com.openclassroom.paymybuddy.service.TransactionService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RelationsService relationsService;


    @Transactional
    public CreateTransactionResult saveTransaction(Integer idReceiver, double amount) {
        CreateTransactionResult createTransactionResult = new CreateTransactionResult();
        User userSender = userService.getLoggedUser();
        User friendReceiver = userService.findUserById(idReceiver);

        if (userSender == null || friendReceiver == null) {
            createTransactionResult.setMessage("Utilisateur envoyeur ou destinataire introuvable.");
            createTransactionResult.setResult(false);
        }

        List<Integer> userFriendIds = relationsService.getUserFriendsId(userSender.getId());
        if (!userFriendIds.contains(friendReceiver.getId())) {
            createTransactionResult.setMessage("Vous n'êtes pas ami avec le destinataire.");
            createTransactionResult.setResult(false);
        }
        double transactionFee = amount * 0.005; // Calcul du pourcentage de 0,5 %

        double senderBalance = userSender.getBalance();
        if (senderBalance < amount + transactionFee) {
            createTransactionResult.setMessage("Solde insuffisant pour effectuer la transaction et payer la monétisation.");
            createTransactionResult.setResult(false);
        }
        if (createTransactionResult.getMessage() != null) {
            return createTransactionResult;
        } else {
            Transaction transaction = new Transaction();
            transaction.setIdUserSender(userSender.getId());
            transaction.setIdUserReceiver(idReceiver);
            transaction.setDate(Instant.now());
            transaction.setFee(transactionFee);
            transaction.setAmount(amount);
            transactionRepository.save(transaction);
            userSender.setBalance(senderBalance - amount - transactionFee);
            userService.updateUser(userSender);
            friendReceiver.setBalance(friendReceiver.getBalance() + amount);
            userService.updateUser(friendReceiver);
            createTransactionResult.setMessage("Transaction created with success");
            createTransactionResult.setResult(true);
            return createTransactionResult;
        }
    }


    public List<Transaction> findTransactionByIdSender(Integer idUser) {
        List<Transaction> transactions = transactionRepository.findTransactionByIdUserSender(idUser);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (transactions != null && !transactions.isEmpty()) {
            transactions.forEach(transaction -> {
                transaction.setUserReceiver(userService.findUserById(transaction.getIdUserReceiver()));
                transaction.setType("send");
                transaction.setFormattedDate(formatter.format(Date.from(transaction.getDate())));
            });
        }
        List<Transaction> transactionReceivs = transactionRepository.findTransactionByIdUserReceiver(idUser);
        if (transactionReceivs != null && !transactionReceivs.isEmpty()) {
            User user = userService.findUserById(idUser);
            transactionReceivs.forEach(transaction -> {
                transaction.setUserReceiver(user);
                transaction.setType("receive");
                transaction.setFormattedDate(formatter.format(Date.from(transaction.getDate())));
            });
            transactions.addAll(transactionReceivs);

        }
        return transactions;

    }


}
