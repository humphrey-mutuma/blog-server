package com.example.blog.user;

import com.example.blog.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    user exits by email
    boolean existsByUsername(String email);
}
