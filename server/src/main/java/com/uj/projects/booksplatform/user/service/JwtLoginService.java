package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.error.exception.BadCredentialsException;
import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.error.exception.NotFoundException;
import com.uj.projects.booksplatform.user.dto.LoginResult;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.resources.LoginResources;
import com.uj.projects.booksplatform.user.wrappers.JwtBuilderWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtLoginService implements LoginService {

    private static final int tokenLifetimeInMinutes = 3600;
    private JwtBuilderWrapper jwtBuilderWrapper;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public JwtLoginService(JwtBuilderWrapper jwtBuilderWrapper, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtBuilderWrapper = jwtBuilderWrapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResult Login(String username, String password) {
        User userWithGivenUsername = userService.getUserByUsername(username);
        if(userWithGivenUsername == null){
            throw new BadCredentialsException("User with given username doesnt exist");
        }
        if(!passwordEncoder.matches(password, userWithGivenUsername.getPassword())){
            throw new BadCredentialsException("Password is incorrect");
        }

        String token = jwtBuilderWrapper.generateToken(username, tokenLifetimeInMinutes, "user", LoginResources.JWT_SECRET);
        return new LoginResult(true, token);
    }
}
