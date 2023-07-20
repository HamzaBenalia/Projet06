package com.openclassroom.paymybuddy.controller;

import com.openclassroom.paymybuddy.model.Transaction;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.service.RelationsService;
import com.openclassroom.paymybuddy.service.UserService;
import com.openclassroom.paymybuddy.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private RelationsService relationsService;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @GetMapping("")
    public String dashBoard(HttpSession httpSession, Model model) {
        User user = userService.getLoggedUser();
        httpSession.setAttribute("userId", user.getId());
        httpSession.setAttribute("firstName", user.getFirstName());
        httpSession.setAttribute("lastName", user.getLastName());
        if (user.getBalance() == null) {
            httpSession.setAttribute("balance", 0);
        } else {
            httpSession.setAttribute("balance", user.getBalance());
        }
        List<User> friends = userService.findUserByIds(relationsService.getUserFriendsId(user.getId()));
        boolean hasFriend = friends != null && !friends.isEmpty();
        model.addAttribute("hasFriend", hasFriend);
        model.addAttribute("friends", friends);
        List<Transaction> transactions = transactionServiceImpl.findTransactionByIdSender(user.getId());
        boolean hasTransaction = transactions != null && !transactions.isEmpty();
        model.addAttribute("hasTransaction", hasTransaction);
        model.addAttribute("transactions", transactions);
        return "index";
    }
}
