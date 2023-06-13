package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.dto.BankAccountDto;
import com.openclassroom.paymybuddy.dto.CreditDto;
import com.openclassroom.paymybuddy.dto.DebitDto;
import com.openclassroom.paymybuddy.dto.FriendDto;
import com.openclassroom.paymybuddy.model.AddFriendResult;
import com.openclassroom.paymybuddy.model.BankAccount;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.service.BankAccountService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        User user = userService.getLoggedUser();
        // Vérifier si l'utilisateur a déjà un compte bancaire enregistré
        if (bankAccountService.findByUserId(user.getId()) != null) {
            result.rejectValue("iban", "", "You already have a bank account.");
        }
        BankAccount existingBankIban = bankAccountService.findBankAccountByIban(bankAccountDto.getIban());

        if (existingBankIban != null) {
            result.rejectValue("iban", "", "There is already an account registered with the same IBAN." +
                    "\nPlease choose another IBAN.");
        }
        if (result.hasErrors()) {
            return "bank";
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban(bankAccountDto.getIban());
        bankAccount.setAmount(Double.parseDouble(bankAccountDto.getSold()));
        bankAccount.setUser(user);
        bankAccountService.saveBankAccount(bankAccount);
        return "users";

    }

    @GetMapping("/debitAccount")
    public String showdebitForm(Model model) {
        // create model object to store form data
        DebitDto debitDto = new DebitDto();
        model.addAttribute("debitDto", debitDto);
        return "debitAccount";
    }

    @PostMapping("/debitAccount")
    public String debitAccount(@Valid @ModelAttribute("debitDto") DebitDto debitDto,
                               BindingResult result,
                               Model model) {
        double amount = Double.parseDouble(debitDto.getAmount());
        boolean debitSuccess = bankAccountService.debitAccount(amount);
        if (debitSuccess) {
            return "redirect:/debitAccount?success";
        } else {
            return "redirect:/debitAccount?failed";
        }

    }

    @GetMapping("/creditAccount")
    public String showCreditForm(Model model) {
        // create model object to store form data
        CreditDto creditDto = new CreditDto();
        model.addAttribute("creditDto", creditDto);
        return "creditAccount";
    }

    @PostMapping("/creditAccount")
    public String debitAccount(@Valid @ModelAttribute("creditDto") CreditDto creditDto,
                               BindingResult result,
                               Model model) {

        double amount = Double.parseDouble(creditDto.getAmount());
        boolean creditSuccess = bankAccountService.creditAccount(amount);
        if (creditSuccess) {
            return "redirect:/creditAccount?success";
        } else {
            return "redirect:/creditAccount?failed";
        }
    }
}