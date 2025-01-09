package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.Sitter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SitterRepository extends JpaRepository<Long, Sitter> {
}
