package com.openclassroom.paymybuddy.service;
import com.openclassroom.paymybuddy.model.AddFriendResult;
import java.util.List;
public interface RelationsService {


    AddFriendResult addFriend(String friendEmail);

    List<Integer> getUserFriendsId(Integer userId);

    boolean removeFriend(Integer userId, Integer friendId);
}
