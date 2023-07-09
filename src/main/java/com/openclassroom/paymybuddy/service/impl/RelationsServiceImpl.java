package com.openclassroom.paymybuddy.service.impl;

import com.openclassroom.paymybuddy.model.AddFriendResult;
import com.openclassroom.paymybuddy.model.Relations;
import com.openclassroom.paymybuddy.model.User;
import com.openclassroom.paymybuddy.repository.RelationsRepository;
import com.openclassroom.paymybuddy.repository.UserRepository;
import com.openclassroom.paymybuddy.service.RelationsService;
import com.openclassroom.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationsServiceImpl implements RelationsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RelationsRepository relationsRepository;

    @Autowired
    private UserService userService;


    public AddFriendResult addFriend(String friendEmail) {
        AddFriendResult addFriendResult = new AddFriendResult();
        User user = userService.getLoggedUser();

        if (user.getEmail().equals(friendEmail)) {
            addFriendResult.setMessage(" You cannot add yourself as a friend");
        }

        User userFriend = userService.findUserByEmail(friendEmail);

        if (userFriend == null) {
            addFriendResult.setMessage("there is no email like that");

        } else {
            List<Integer> userFriendIds = getUserFriendsId(user.getId());
            if (userFriendIds.contains(userFriend.getId())) {
                addFriendResult.setMessage("You already have this friend in your friendList");
            }
        }

        if (addFriendResult.getMessage() != null) {
            addFriendResult.setResult(false);
        } else {
            Relations relations = new Relations(user.getId(), userFriend.getId());
            relationsRepository.save(relations);
            addFriendResult.setResult(true);
        }
        return addFriendResult;
    }

    @Override
    public List<Integer> getUserFriendsId(Integer userId) {
        List<Relations> relations = relationsRepository.findAllRelationsByIdUser(userId);
        return relations.stream().map(Relations::getIdFriend).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean removeFriend(Integer userId, Integer friendId) {
        List<Integer> userFriendIds = getUserFriendsId(userId);
        if (!userFriendIds.contains(friendId)) {
            return false;
        } else {
            relationsRepository.deleteFriendFromId(userId, friendId);
            return true;
        }
    }
}

