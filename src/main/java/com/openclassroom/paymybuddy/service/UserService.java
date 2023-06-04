package com.openclassroom.paymybuddy.service;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
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
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
