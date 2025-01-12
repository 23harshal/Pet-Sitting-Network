package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.Pet;
import com.petSitting.petSitting.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository< Pet , Long > {
    List<Pet> findByOwner(User owner);
}
