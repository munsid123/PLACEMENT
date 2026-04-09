package com.placement.management.service;

import com.placement.management.entity.User;
import com.placement.management.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User updateUser(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setContactNo(updatedUser.getContactNo());
        existingUser.setStream(updatedUser.getStream());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(userId);
    }
}