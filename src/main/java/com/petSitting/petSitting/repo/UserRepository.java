package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
    User findByEmailId(String emailId);
}
