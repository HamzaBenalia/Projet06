package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.forms.UserForm;
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

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("userForm") UserForm userForm,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userForm.getEmail());

        if (existingUser != null) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("userForm", userForm);
            return "/register";
        }
        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setBalance(0.0);
        user.setPassword(userForm.getPassword());
        userService.saveUser(user);
        return "redirect:/login?registered";
    }
}
