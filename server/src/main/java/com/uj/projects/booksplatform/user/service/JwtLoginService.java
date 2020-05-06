package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.dto.LoginResult;
import com.uj.projects.booksplatform.user.resources.LoginResources;
import com.uj.projects.booksplatform.user.wrappers.JwtBuilderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtLoginService implements LoginService {

    private static final int tokenLifetimeInMinutes = 3600;
    private JwtBuilderWrapper jwtBuilderWrapper;

    @Autowired
    public JwtLoginService(JwtBuilderWrapper jwtBuilderWrapper) {
        this.jwtBuilderWrapper = jwtBuilderWrapper;
    }

    @Override
    public LoginResult Login(String username) {
        String token = jwtBuilderWrapper.generateToken(username, tokenLifetimeInMinutes, "user", LoginResources.JWT_SECRET);
        return new LoginResult(true, token);
    }
}
