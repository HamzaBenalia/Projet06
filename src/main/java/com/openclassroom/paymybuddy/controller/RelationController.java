package com.openclassroom.paymybuddy.controller;
import com.openclassroom.paymybuddy.dto.FriendDto;
import com.openclassroom.paymybuddy.dto.UserDto;
import com.openclassroom.paymybuddy.model.AddFriendResult;
import com.openclassroom.paymybuddy.model.Relations;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.RelationsRepository;
import com.openclassroom.paymybuddy.service.RelationsService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Controller
public class RelationController {

    @Autowired
    private RelationsService relationsService;

    @Autowired
    private UserService userService;

    @GetMapping("/friends")
    public String showFriendList(Model model) {
        // create model object to store form data
        FriendDto friendrDto = new FriendDto();
        model.addAttribute("friendDto", friendrDto);
        return "friend";
    }


    @PostMapping("/friends")
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

    @GetMapping("/friendList")
    public String showAllFriends(Model model) {
        User user = userService.getLoggedUser();
        Integer idLogger = user.getId();
        List<Relations> relations = user.getRelations();
        List<User> friends = new ArrayList<>();
        for(Relations relation: relations){
            if(idLogger.equals(relation.getIdUser())){
                //userService.findUserById(relation.getIdFriend());
                friends.add(userService.findUserById(relation.getIdFriend()));
            }
        }

        model.addAttribute("friends", friends);
        return "/friendList";
    }

    @GetMapping("/deleteFriend/{friendId}")
    public String deleteFriend(@PathVariable("friendId") Integer friendId) {
        User user = userService.getLoggedUser();
        boolean delete = relationsService.removeFriend(user.getId(), friendId);
        return "redirect:/friendList";
    }


}
