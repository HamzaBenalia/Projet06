package com.openclassroom.paymybuddy.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditForm {
    @NotBlank(message = "amount must not be blank")
    private String amount;
}
