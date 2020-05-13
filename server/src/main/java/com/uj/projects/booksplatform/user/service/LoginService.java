package com.uj.projects.booksplatform.user.service;


import com.uj.projects.booksplatform.user.dto.LoginResult;

public interface LoginService {
    LoginResult Login(String username, String password);
}
