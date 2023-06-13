package com.openclassroom.paymybuddy.repository;
import com.openclassroom.paymybuddy.dto.UserDto;
import com.openclassroom.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query
    List<User> findByfirstName(String firstName);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

}
