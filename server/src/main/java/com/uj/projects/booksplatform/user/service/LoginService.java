package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.entity.LoginResult;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public LoginResult Login(String username, String password);
}
