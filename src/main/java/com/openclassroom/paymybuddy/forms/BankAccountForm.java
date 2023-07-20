package com.openclassroom.paymybuddy.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountForm {

    @NotBlank(message = "Iban must not be blank")
    private String iban;
    @NotBlank(message = "sold is required")
    private String sold;
}
