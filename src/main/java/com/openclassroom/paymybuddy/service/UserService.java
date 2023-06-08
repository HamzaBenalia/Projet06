package com.openclassroom.paymybuddy.service;

import com.openclassroom.paymybuddy.model.User;

import java.util.List;

public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

    List<User> findAll();

  User getLoggedUser();




}
