package com.petSitting.petSitting.controllers;

import com.petSitting.petSitting.dto.ServiceRequest;
import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.service.SitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sitters")
public class SitterController {

    @Autowired
    private SitterService sitterService;

    /**
     * Create a new Sitter.
     */
    @PostMapping
    public ResponseEntity<?> createSitter(@RequestBody Sitter sitter) {
        try {
            Sitter createdSitter = sitterService.createSitter(sitter);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSitter);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Get a Sitter by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getSitterById(@PathVariable Long id) {
        try {
            Sitter sitter = sitterService.getSitterById(id);
            if (sitter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sitter not found");
            }
            return ResponseEntity.ok(sitter);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Update a Sitter.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSitter(@PathVariable Long id, @RequestBody Sitter updatedSitter) {
        try {
            Sitter updated = sitterService.updateSitter(id, updatedSitter);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Delete a Sitter by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSitter(@PathVariable Long id) {
        try {
            sitterService.deleteSitter(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{sitterId}/services/add")
    public ResponseEntity<Sitter> addMultipleServices(
            @PathVariable Long sitterId,
            @RequestBody List<ServiceRequest> servicesToAdd) {
        Sitter updatedSitter = sitterService.addMultipleServices(sitterId, servicesToAdd);
        return ResponseEntity.ok(updatedSitter);
    }

    @PatchMapping("/{sitterId}/services/remove")
    public ResponseEntity<Sitter> removeMultipleServices(
            @PathVariable Long sitterId,
            @RequestBody List<String> servicesToRemove) {
        Sitter updatedSitter = sitterService.removeMultipleServices(sitterId, servicesToRemove);
        return ResponseEntity.ok(updatedSitter);
    }

    @PutMapping("/{sitterId}/services/update")
    public ResponseEntity<Sitter> updateMultipleServices(
            @PathVariable Long sitterId,
            @RequestBody List<ServiceRequest> servicesToUpdate) {
        Sitter updatedSitter = sitterService.updateMultipleServices(sitterId, servicesToUpdate);
        return ResponseEntity.ok(updatedSitter);
    }

}
