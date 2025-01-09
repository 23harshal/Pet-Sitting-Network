package com.petSitting.petSitting.service;

import com.petSitting.petSitting.models.User;
import com.petSitting.petSitting.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        try{
          User existedUser = userRepository.findByEmailId(user.getEmailId());
          if(existedUser!=null){
              throw new Exception("Email already exists");
          }

            userRepository.save(user);
            return user;
        }
        catch (Exception e){
            throw new Exception("something went wrong while saving user");
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String emailId){
        User user =  userRepository.findByEmailId(emailId);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User user = getUserById(id);
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
