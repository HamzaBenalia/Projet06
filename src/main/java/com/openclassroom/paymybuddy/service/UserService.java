package com.openclassroom.paymybuddy.service;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    User findUserById(String email);

    User findUserById(Integer id);

    void saveUser(User user);

    List<User> findAll();

    User getLoggedUser();


    List<User> findFriends(User user);

    void deleteFriend(Integer id);

    void updateUser(User user);

    void updateBankAccount(BankAccount bankAccount);
}
