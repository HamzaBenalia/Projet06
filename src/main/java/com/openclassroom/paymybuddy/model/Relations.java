package com.openclassroom.paymybuddy.model;
import com.openclassroom.paymybuddy.model.compositekeys.RelationId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RELATIONS")
@IdClass(RelationId.class)
public class Relations {

    @Id
    @Column(name = "ID_USER", updatable = false)
    private Integer idUser;

    @Id
    @Column(name = "ID_FRIEND", updatable = false)
    private Integer idFriend;
}
