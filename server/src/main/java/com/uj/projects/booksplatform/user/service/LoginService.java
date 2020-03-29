package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.entity.LoginResult;

public interface LoginService {
    public LoginResult Login(String username, String password);
}
