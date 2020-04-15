package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.error.exception.DefaultRuntimeException;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void validateIfUserAlreadyRegistered(String username, String email) {
        Map<String, String> errors = new HashMap<>();
        User userWithGivenUsername = userRepository.findUserByUsername(username);
        User userWithGivenEmail = userRepository.findUserByEmail(email);
        if(userWithGivenUsername != null){
            errors.put("username", "Użytkownik o podanym username już istnieje");
        }
        if(userWithGivenEmail != null){
            errors.put("email", "Użytkownik o podanym email już istnieje");
        }
        if(!errors.isEmpty()){
            throw new DefaultRuntimeException(errors);
        }
    }
}
