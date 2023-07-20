package com.openclassroom.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    // handler method to handle list of users
//    @GetMapping("/users")
//    public String users(Model model) {
//
//        List<UserForm> users = userService.findAll().stream()
//                .map((user) -> mapToUserDto(user))
//                .collect(Collectors.toList());
//        model.addAttribute("users", users);
//        return "/users";
//    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    private UserForm mapToUserDto(User user) {
//        UserForm userForm = new UserForm();
//        userForm.setFirstName(user.getFirstName());
//        userForm.setLastName(user.getLastName());
//        userForm.setEmail(user.getEmail());
//        return userForm;
//    }
}