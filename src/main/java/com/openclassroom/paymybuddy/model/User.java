package com.openclassroom.paymybuddy.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", updatable = false, nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "BALANCE")
    private Double balance;

    @OneToOne(mappedBy = "user")
    private BankAccount bankAccount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER_SENDER")
    private List<Transaction> transactionSend;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER_RECEIVER")
    private List<Transaction> transactionReceive;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "idUser")
    private List<Relations> relations;
}
