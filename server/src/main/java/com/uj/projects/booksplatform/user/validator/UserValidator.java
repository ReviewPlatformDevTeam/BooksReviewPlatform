package com.uj.projects.booksplatform.user.validator;

import com.uj.projects.booksplatform.error.exception.AlreadyExistsException;
import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserValidator {
    private UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void validateIfUserAlreadyRegistered(String username, String email) {
        User userWithGivenUsername = userRepository.findUserByUsername(username);
        User userWithGivenEmail = userRepository.findUserByEmail(email);
        if(userWithGivenUsername != null){
            throw new AlreadyExistsException("User with given username is already registered");
        }
        if(userWithGivenEmail != null){
            throw new AlreadyExistsException("User with given email is already registered");
        }
    }
}
