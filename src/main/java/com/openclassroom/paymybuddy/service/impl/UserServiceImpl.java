package com.openclassroom.paymybuddy.service.impl;

import com.openclassroom.paymybuddy.dto.UserDto;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.UserRepository;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
}