package com.openclassroom.paymybuddy.controller;

import com.openclassroom.paymybuddy.dto.BankAccountDto;
import com.openclassroom.paymybuddy.dto.UserDto;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.service.BankAccountService;
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
public class BankAccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/bank")
    public String showBankAccount(Model model) {
        // create model object to store form data
        BankAccountDto bankAccountDto = new BankAccountDto();
        model.addAttribute("bankAccountDto", bankAccountDto);
        return "bank";
    }

    @PostMapping("/bank")
    public String saveBankAccount(@Valid @ModelAttribute("bankAccountDto") BankAccountDto bankAccountDto,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bankAccountDto", bankAccountDto);
            return "/bank";
        }
        User user = userService.getLoggedUser();
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban(bankAccountDto.getIban());
        bankAccount.setUser(user);
        bankAccountService.saveBankAccount(bankAccount);
        return "redirect:/bank?success";
    }
}
