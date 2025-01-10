package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository< Pet , Long > {
}
