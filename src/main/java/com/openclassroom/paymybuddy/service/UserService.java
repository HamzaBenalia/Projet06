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

    void updateUser(User user);

    List<User> findUserByIds(List<Integer> ids);

}
