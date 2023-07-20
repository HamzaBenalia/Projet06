package com.openclassroom.paymybuddy.controller;

import com.openclassroom.paymybuddy.dto.FriendDto;
import com.openclassroom.paymybuddy.model.AddFriendResult;
import com.openclassroom.paymybuddy.model.Relations;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.service.RelationsService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RelationController {

    @Autowired
    private RelationsService relationsService;

    @Autowired
    private UserService userService;

    @GetMapping("/friends")
    public String showAddFriendForm(Model model) {
        // create model object to store form data
        FriendDto friendrDto = new FriendDto();
        model.addAttribute("friendDto", friendrDto);
        return "friend";
    }


    @GetMapping("/friend")
    public String showAllFriends(Model model) {
        User user = userService.getLoggedUser();
        Integer idLogger = user.getId();
        List<Relations> relations = user.getRelations();
        List<User> friends = new ArrayList<>();
        for (Relations relation : relations) {
            if (idLogger.equals(relation.getIdUser())) {
                //userService.findUserById(relation.getIdFriend());
                friends.add(userService.findUserById(relation.getIdFriend()));
            }
        }
        model.addAttribute("friends", friends);
        return "/index";
    }

    @GetMapping("/friendList")
    public String showFriends(Model model) {
        User user = userService.getLoggedUser();
        Integer idLogger = user.getId();
        List<Relations> relations = user.getRelations();
        List<User> friends = new ArrayList<>();
        for (Relations relation : relations) {
            if (idLogger.equals(relation.getIdUser())) {
                //userService.findUserById(relation.getIdFriend());
                friends.add(userService.findUserById(relation.getIdFriend()));
            }
        }
        model.addAttribute("friends", friends);
        return "/friendList";
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
            return "redirect:/friends?success";
        }
    }

    @GetMapping("/deleteFriend/{friendId}")
    public String deleteFriend(@PathVariable("friendId") Integer friendId) {
        User user = userService.getLoggedUser();
        boolean delete = relationsService.removeFriend(user.getId(), friendId);
        return "redirect:/?deletedFriend";
    }
}
