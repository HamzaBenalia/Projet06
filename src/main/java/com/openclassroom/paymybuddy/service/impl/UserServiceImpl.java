package com.openclassroom.paymybuddy.service.impl;

import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.UserRepository;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User enregistrerUtilisateur(User user) {
        return userRepository.save(user);
    }

    public List<User> obtenirUtilisateursParNom(String firstName) {
        return userRepository.findByfirstName(firstName);
    }

    public User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        } else {
            return optionalUser.get();
        }
    }

    @Override
    public User findUserById(String email) {
        return null;
    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        } else {
            return optionalUser.get();
        }
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private String getEmailOfConnectedUser() {
        String email = null;
        SecurityContext context = SecurityContextHolder.getContext();
        Object principal = context.getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }

    public User getLoggedUser() {
        return userRepository.findByEmail(getEmailOfConnectedUser()).get();
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        User existUser = findUserById(user.getId());
        if (existUser != null) {
            existUser.setBalance(user.getBalance());
            existUser.setLastName(user.getLastName());
            existUser.setFirstName(user.getFirstName());
            userRepository.save(existUser);
        }
    }

    @Override
    public List<User> findUserByIds(List<Integer> ids) {
        return userRepository.findAllById(ids);
    }
}