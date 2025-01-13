package com.petSitting.petSitting.service;

import com.petSitting.petSitting.dto.PetDTO;
import com.petSitting.petSitting.dto.UserDTO;
import com.petSitting.petSitting.models.Pet;
import com.petSitting.petSitting.models.User;
import com.petSitting.petSitting.repo.PetRepository;
import com.petSitting.petSitting.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public PetDTO getPetById(Long id){
       try{
            Pet pet = petRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("Pet not found"));

            return new PetDTO(
                    pet.getId(),
                    pet.getPetName(),
                    new UserDTO(pet.getOwner().getId() ,pet.getOwner().getFirstName() , pet.getOwner().getLastName())
            );
       }
       catch (Exception e){
           throw new RuntimeException("Error getting pet by ID");
       }
    }

    public List<PetDTO> getPetsByOwner(Long ownerId) {
       try{
           User owner = userRepository.findById(ownerId)
                   .orElseThrow(() -> new RuntimeException("Owner not found with ID: " + ownerId));

           List<Pet> pets = petRepository.findByOwner(owner);
           UserDTO userDTO = new UserDTO(ownerId , owner.getFirstName(), owner.getLastName());
           if(pets.isEmpty()){
               throw new RuntimeException("no pets found for this owner");
           }
           List<PetDTO> petDTOS = new ArrayList<>();
           for (Pet pet : pets){
               petDTOS.add(new PetDTO(pet.getId(), pet.getPetName(),  userDTO));
           }
           return petDTOS;
       }
       catch (Exception e){
           throw new RuntimeException("Error getting pets by owner");
       }

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
