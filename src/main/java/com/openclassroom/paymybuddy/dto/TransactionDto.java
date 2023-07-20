package com.openclassroom.paymybuddy.dto;
import com.openclassroom.paymybuddy.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    @NotBlank(message = "the amount must not be blank")
    private String amount;
    @NotNull(message = "the userReceiver must not be null")
    private Integer idUserReceiver;

    private List<User> friends;
}
