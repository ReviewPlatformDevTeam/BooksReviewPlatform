package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(User user);
    void deleteUser(Integer id);
    User updateUser(User user);
    String resetPassword(String Email);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}
