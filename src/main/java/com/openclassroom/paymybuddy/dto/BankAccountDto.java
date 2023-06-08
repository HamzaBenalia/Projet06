package com.openclassroom.paymybuddy.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {

    @NotBlank(message = "Iban must not be blank")
    private String iban;
}
