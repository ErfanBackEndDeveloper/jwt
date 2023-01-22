package com.example.jwt.repository;

import com.example.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
//@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);
}
