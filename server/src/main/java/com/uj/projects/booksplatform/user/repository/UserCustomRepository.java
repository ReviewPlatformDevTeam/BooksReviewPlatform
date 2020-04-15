package com.uj.projects.booksplatform.user.repository;

import com.uj.projects.booksplatform.user.entity.User;
import org.springframework.data.jpa.repository.Query;


public interface UserCustomRepository {
    @Query(value = "SELECT * FROM users u WHERE u.username = ?",
            nativeQuery = true)
    User findUserByUsername(String username);

    @Query(value = "SELECT * FROM users u WHERE u.email = ?",
            nativeQuery = true)
    User findUserByEmail(String username);
}
