package com.uj.projects.booksplatform.user.validator;

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

    public boolean validateIfUserAlreadyRegistered(String username, String email) {
        Map<String, String> errors = new HashMap<>();
        User userWithGivenUsername = userRepository.findUserByUsername(username);
        User userWithGivenEmail = userRepository.findUserByEmail(email);
        if(userWithGivenUsername != null){
            errors.put("username", "User with given username is already registered");
        }
        if(userWithGivenEmail != null){
            errors.put("email", "User with given email is already registered");
        }
        if(!errors.isEmpty()){
            throw new DefaultRuntimeException(errors);
        }
        return false;
    }
}
