package com.openclassroom.paymybuddy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDto {

    @Email(message = "Email must be a valid email")
    @NotBlank(message = "Email must not be blank")
    private String email;
}
