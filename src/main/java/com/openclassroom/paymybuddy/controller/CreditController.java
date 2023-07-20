package com.openclassroom.paymybuddy.controller;

import com.openclassroom.paymybuddy.forms.CreditForm;
import com.openclassroom.paymybuddy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@EnableTransactionManagement
public class CreditController {


    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/creditAccount")
    public String showCreditForm(Model model) {
        // create model object to store form data
        CreditForm creditForm = new CreditForm();
        model.addAttribute("creditForm", creditForm);
        return "creditAccount";
    }

    @PostMapping("/creditAccount")
    public String debitAccount(@Valid @ModelAttribute("creditForm") CreditForm creditForm,
                               BindingResult result,
                               Model model) {

        double amount = Double.parseDouble(creditForm.getAmount());
        boolean creditSuccess = bankAccountService.creditAccount(amount);
        if (creditSuccess) {
            return "redirect:/?creditAccountSuccess";
        } else {
            return "redirect:/creditAccount?failed";
        }
    }

}
