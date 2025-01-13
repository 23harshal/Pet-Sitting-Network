package com.petSitting.petSitting.controllers;

import com.petSitting.petSitting.dto.PetDTO;
import com.petSitting.petSitting.models.Pet;
import com.petSitting.petSitting.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    // Create a pet
    @PostMapping("/{ownerId}")
    public ResponseEntity<Pet> createPet(@PathVariable Long ownerId, @RequestBody Pet pet) {
        Pet createdPet = petService.createPet(pet, ownerId);
        return ResponseEntity.ok(createdPet);
    }

    // Get a pet by ID
    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long petId) {
        PetDTO pet = petService.getPetById(petId);
        return ResponseEntity.ok(pet);
    }

    // Get all pets by owner ID
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable Long ownerId) {
        List<PetDTO> pets = petService.getPetsByOwner(ownerId);
        return ResponseEntity.ok(pets);
    }

    // Update a pet
    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long petId, @RequestBody Pet updatedPet) {
        Pet pet = petService.updatePet(petId, updatedPet);
        return ResponseEntity.ok(pet);
    }

    // Delete a pet
    @DeleteMapping("/{petId}")
    public ResponseEntity<String> deletePet(@PathVariable Long petId) {
        petService.deletePet(petId);
        return ResponseEntity.ok("Pet deleted successfully");
    }
}
