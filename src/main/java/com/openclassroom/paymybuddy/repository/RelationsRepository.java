package com.openclassroom.paymybuddy.repository;

import com.openclassroom.paymybuddy.model.Relations;
import com.openclassroom.paymybuddy.model.compositekeys.RelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationsRepository extends JpaRepository<Relations, RelationId> {

    boolean existsById(RelationId relationId);

    List<Relations> findAllRelationsByIdUser(Integer idUser);

    @Modifying
    @Query(value = "DELETE FROM Relations r WHERE r.idUser = :idUser AND r.idFriend = :idFriend")
    void deleteFriendFromId(@Param("idUser") Integer idUser, @Param("idFriend") Integer idFriend);
}

