package com.uj.projects.booksplatform.user.service;

import com.uj.projects.booksplatform.user.entity.User;
import com.uj.projects.booksplatform.user.repository.UserRepository;
import com.uj.projects.booksplatform.user.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserValidator userValidator;
    private RandomPasswordGenerator randomPasswordGenerator;

    @Autowired
    UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator userValidator, RandomPasswordGenerator randomPasswordGenerator){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
        this.randomPasswordGenerator = randomPasswordGenerator;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        boolean alreadyExists = userValidator.validateIfUserAlreadyRegistered(user.getUsername(), user.getEmail());
        if(alreadyExists){
            return null;
        }
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
    public String resetPassword(String email) throws UserNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new UserNotFoundException();
        }
        String newPassword = randomPasswordGenerator.generate();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return newPassword;
    }
}
