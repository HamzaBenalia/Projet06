package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.forms.DebitForm;
import com.openclassroom.paymybuddy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class DebitController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/debitAccount")
    public String showdebitForm(Model model) {
        // create model object to store form data
        DebitForm debitForm = new DebitForm();
        model.addAttribute("debitForm", debitForm);
        return "debitAccount";
    }

    @PostMapping("/debitAccount")
    public String debitAccount(@Valid @ModelAttribute("debitForm") DebitForm debitForm,
                               BindingResult result,
                               Model model) {
        double amount = Double.parseDouble(debitForm.getAmount());
        boolean debitSuccess = bankAccountService.debitAccount(amount);
        if (debitSuccess) {
            return "redirect:/?debitAccountSuccess";
        } else {
            return "redirect:/debitAccount?failed";
        }
    }
}
