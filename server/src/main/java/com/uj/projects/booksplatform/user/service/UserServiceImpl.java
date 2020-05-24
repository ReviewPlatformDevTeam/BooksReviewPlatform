package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.error.exception.NotFoundException;
import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import com.uj.projects.booksplatform.user.validator.UserValidator;
import com.uj.projects.booksplatform.user.wrappers.PasswordBuilderWrapper;
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
    private UserValidator userValidator;
    private PasswordBuilderWrapper passwordBuilderWrapper;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator userValidator, PasswordBuilderWrapper passwordBuilderWrapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
        this.passwordBuilderWrapper = passwordBuilderWrapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        userValidator.validateIfUserAlreadyRegistered(user.getUsername(), user.getEmail());
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
    public String resetPassword(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            Map<String, String> errors = new HashMap<>();
            errors.put("user", "User with email " + email + " not found.");
            throw new NotFoundException(errors);
        }
        String newPassword = passwordBuilderWrapper.generatePassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return newPassword;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
