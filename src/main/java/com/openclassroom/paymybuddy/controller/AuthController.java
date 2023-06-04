package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.dto.UserDto;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // handler method to handle home page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
            return "register";
        } else {
            User user = new User();
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setBalance(0.0);
            userService.saveUser(user);
            return "redirect:/register?success";
        }
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
