package com.petSitting.petSitting.controllers;

import com.petSitting.petSitting.dto.ServiceRequest;
import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.service.SitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}/add-service")
    public ResponseEntity<?> addService(@PathVariable Long id , @RequestBody ServiceRequest serviceRequest){
        try {
            Sitter updated = sitterService.addService(id, serviceRequest);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/remove-service")
    public ResponseEntity<?> removeService(@PathVariable Long id, @RequestBody String serviceName){
        try {
            Sitter updated = sitterService.removeService(id, serviceName);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{sitterId}/service/update")
    public ResponseEntity<?> updateService(
            @PathVariable Long sitterId,
            @RequestParam String oldServiceName,
            @RequestBody ServiceRequest updatedService) {
        try{
            Sitter updatedSitter = sitterService.updateService(sitterId, oldServiceName, updatedService);
            return ResponseEntity.ok(updatedSitter);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
