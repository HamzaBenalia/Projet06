package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.forms.BankAccountForm;
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
        User user = userService.getLoggedUser();
        boolean hasBankAccount = bankAccountService.findByUserId(user.getId()) != null;
        model.addAttribute("hasBankAccount", hasBankAccount);
        model.addAttribute("bankAccountForm", new BankAccountForm());
        return "bank";
    }

    /*@GetMapping("/bank")
    public String showBankAccount(Model model) {
        // create model object to store form data
        BankAccountForm bankAccountForm = new BankAccountForm();
        model.addAttribute("bankAccountForm", bankAccountForm);
        User user = userService.getLoggedUser();
        // Vérifier si l'utilisateur a déjà un compte bancaire enregistré
        boolean hasBankAccount = bankAccountService.findByUserId(user.getId()) != null;
        model.addAttribute("hasBankAccount", hasBankAccount);
        return "bank";
    }*/

    @PostMapping("/bank")
    public String saveBankAccount(@Valid @ModelAttribute("bankAccountForm") BankAccountForm bankAccountForm,
                                  BindingResult result,
                                  Model model) {
        User user = userService.getLoggedUser();
        // Vérifier si l'utilisateur a déjà un compte bancaire enregistré
        if (bankAccountService.findByUserId(user.getId()) != null) {
            result.rejectValue("iban", "", "You already have a bank account.");
        }
        BankAccount existingBankIban = bankAccountService.findBankAccountByIban(bankAccountForm.getIban());

        if (existingBankIban != null) {
            result.rejectValue("iban", "", "There is already an account registered with the same IBAN." +
                    "\nPlease choose another IBAN.");
        }
        if (result.hasErrors()) {
            return "bank";
        }
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban(bankAccountForm.getIban());
        bankAccount.setAmount(Double.parseDouble(bankAccountForm.getSold()));
        bankAccount.setUser(user);
        bankAccountService.saveBankAccount(bankAccount);
        return "redirect:/bank?success";
    }

    @PostMapping("/bank/update")
    public String updateBankAccount(@Valid @ModelAttribute("bankAccountForm") BankAccountForm bankAccountForm,
                                    BindingResult result,
                                    Model model) {
        User user = userService.getLoggedUser();
        BankAccount existingBankAccount = bankAccountService.findByUserId(user.getId());

        if (existingBankAccount != null) {
            existingBankAccount.setIban(bankAccountForm.getIban());
            bankAccountService.saveBankAccount(existingBankAccount);
            return "redirect:/bank?success";
        }

        // Gérer le cas où aucun compte bancaire existant n'est trouvé

        return "redirect:/bank";
    }
}