package com.openclassroom.paymybuddy.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFriendResult {

    private String message;
    private boolean result;
}
