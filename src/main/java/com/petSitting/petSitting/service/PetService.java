package com.petSitting.petSitting.service;

import com.petSitting.petSitting.models.Pet;
import com.petSitting.petSitting.models.User;
import com.petSitting.petSitting.repo.PetRepository;
import com.petSitting.petSitting.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    public Pet createPet(Pet pet , Long ownerId){
        try{
            User owner = userRepository.findById(ownerId)
                    .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));

            pet.setOwner(owner);
            return petRepository.save(pet);
        }
        catch (Exception e){
            throw new RuntimeException("Error creating pet");
        }
    }

    public Pet getPetById(Long id){
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));

        return petRepository.findByOwner(owner);
    }

    public Pet updatePet(Long petId, Pet updatedPet) {
        try{
            Pet existingPet = petRepository.findById(petId)
                    .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + petId));

            if (updatedPet.getPetName() != null) existingPet.setPetName(updatedPet.getPetName());
            if (updatedPet.getType() != null) existingPet.setType(updatedPet.getType());
            if (updatedPet.getBreed() != null) existingPet.setBreed(updatedPet.getBreed());
            if (updatedPet.getAge() != null) existingPet.setAge(updatedPet.getAge());
            if (updatedPet.getDescription() != null) existingPet.setDescription(updatedPet.getDescription());

            return petRepository.save(existingPet);
        }catch (Exception e){
            throw new RuntimeException("Error updating pet");
        }
    }

    public void deletePet(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new RuntimeException("Pet not found with ID: " + petId);
        }
        petRepository.deleteById(petId);
    }

}
