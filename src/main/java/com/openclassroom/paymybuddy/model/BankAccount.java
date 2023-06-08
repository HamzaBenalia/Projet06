package com.openclassroom.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BANK_ACOUNT")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "IBAN")
    private String iban;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    private User user;
}

