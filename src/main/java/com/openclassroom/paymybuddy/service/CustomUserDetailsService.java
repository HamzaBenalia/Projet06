package com.openclassroom.paymybuddy.service;

import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isEmpty()) {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());

        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
