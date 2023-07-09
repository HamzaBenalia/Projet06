package com.openclassroom.paymybuddy.model.compositekeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationId implements Serializable {

    private Integer idUser;
    private Integer idFriend;
}
