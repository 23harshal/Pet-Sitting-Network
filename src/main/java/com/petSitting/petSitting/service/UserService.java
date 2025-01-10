package com.petSitting.petSitting.service;

import com.petSitting.petSitting.models.User;
import com.petSitting.petSitting.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        try {
            User existedUser = userRepository.findByEmailId(user.getEmailId());
            if (existedUser != null) {
                throw new Exception("Email already exists");
            }

            userRepository.save(user);
            return user;
        } catch (Exception e) {
            throw new Exception("something went wrong while saving user");
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String emailId) {
        User user = userRepository.findByEmailId(emailId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User existingUser = getUserById(id);


        // Sanitize and update the fields
        if (updatedUser.getFirstName() != null && !updatedUser.getFirstName().trim().isEmpty()) {
            existingUser.setFirstName(updatedUser.getFirstName().trim());
        }

        if (updatedUser.getLastName() != null && !updatedUser.getLastName().trim().isEmpty()) {
            existingUser.setLastName(updatedUser.getLastName().trim());
        }

        if (updatedUser.getEmailId() != null && !updatedUser.getEmailId().trim().isEmpty()) {
            existingUser.setEmailId(updatedUser.getEmailId().trim());
        }

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().trim().isEmpty()) {
            existingUser.setPassword(updatedUser.getPassword().trim());
        }

        if (updatedUser.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }


}
