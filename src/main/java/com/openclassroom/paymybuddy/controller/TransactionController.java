package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.dto.TransactionDto;
import com.openclassroom.paymybuddy.model.CreateTransactionResult;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.service.RelationsService;
import com.openclassroom.paymybuddy.service.TransactionService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TransactionController {

    private List<User> friends;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RelationsService relationsService;


    @GetMapping("/transaction")
    public String showTransactionForm(Model model) {
        TransactionDto transactionDto = new TransactionDto();
        User loggedUser = userService.getLoggedUser();
        List<Integer> idFriends = relationsService.getUserFriendsId(loggedUser.getId());
        friends = userService.findUserByIds(idFriends);
        transactionDto.setFriends(friends);
        model.addAttribute("transactionDto", transactionDto);
        return "transaction";

    }

    @PostMapping("/transaction")
    public String saveTransaction(@Valid @ModelAttribute("transactionDto") TransactionDto transactionDto, BindingResult result, Model model, HttpSession httpSession) {
        double amount = Double.parseDouble(transactionDto.getAmount());
        Integer idUserReceiver = transactionDto.getIdUserReceiver();
        CreateTransactionResult createTransactionResult = transactionService.saveTransaction(idUserReceiver, amount);
        if (!createTransactionResult.isResult()) {
            result.rejectValue("amount", "",
                    createTransactionResult.getMessage());
            transactionDto.setFriends(friends);
            return "transaction";
        } else {
            User user = userService.getLoggedUser();
            if (user.getBalance() == null) {
                httpSession.setAttribute("balance", 0);
            } else {
                httpSession.setAttribute("balance", user.getBalance());
            }
            return "redirect:/transaction?success";
        }
    }
}
