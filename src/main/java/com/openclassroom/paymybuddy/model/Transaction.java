package com.openclassroom.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Integer id;

    @Column(name = "ID_USER_SENDER")
    private Integer idUserSender;

    @Column(name = "ID_USER_RECEIVER")
    private Integer idUserReceiver;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "DATE")
    private Instant date;

    @Column(name = "FEE")
    private Double fee;

    @Transient
    private User userReceiver;

    @Transient
    private String type;

    @Transient
    private String formattedDate;
}
