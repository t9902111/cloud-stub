package com.example.authentication.repository;

import com.example.authentication.model.Role;
import com.example.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.enabled = TRUE")
    List<User> findAllActive();

    @Query("SELECT u FROM User u JOIN FETCH u.roles r JOIN FETCH r.privileges p WHERE u.username = ?1")
    User getDetailsByUsername(String username);
}
