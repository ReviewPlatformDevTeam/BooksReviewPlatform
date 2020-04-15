package com.uj.projects.booksplatform.user.repository;

import com.uj.projects.booksplatform.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserCustomRepository {
    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM User u WHERE u.email= :email")
    User findUserByEmail(@Param("email") String email);
}
