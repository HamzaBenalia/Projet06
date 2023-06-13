package com.openclassroom.paymybuddy.controller;

import com.openclassroom.paymybuddy.dto.FriendDto;
import com.openclassroom.paymybuddy.model.AddFriendResult;
import com.openclassroom.paymybuddy.service.RelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RelationController {

    @Autowired
    private RelationsService relationsService;

    @GetMapping("/friend")
    public String showFriendList(Model model) {
        // create model object to store form data
        FriendDto friendrDto = new FriendDto();
        model.addAttribute("friendDto", friendrDto);
        return "friend";
    }


    @PostMapping("/friend")
    public String addFriend(@Valid @ModelAttribute("friendDto") FriendDto friendDto,
                            BindingResult result,
                            Model model) {

        AddFriendResult addFriendResult = relationsService.addFriend(friendDto.getEmail());

        if (!addFriendResult.isResult()) {
            result.rejectValue("email", "",
                    addFriendResult.getMessage());
            return "friend";
        } else {
            return "redirect:/friend?success";
        }
    }
}
