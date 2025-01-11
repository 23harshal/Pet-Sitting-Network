package com.petSitting.petSitting.service;

import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.repo.SitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SitterService {

    @Autowired
    private SitterRepository sitterRepository;

    /**
     * Creates a new Sitter account.
     *
     * @param sitter Sitter object containing details.
     * @return Newly created Sitter.
     */
    public Sitter createSitter(Sitter sitter) {
        // Check if email already exists
        if (sitterRepository.findByEmailId(sitter.getEmailId()) != null) {
            throw new RuntimeException("Email already exists: " + sitter.getEmailId());
        }

        return sitterRepository.save(sitter);
    }

    /**
     * Fetches a Sitter by ID.
     *
     * @param id Sitter ID.
     * @return Found Sitter.
     */
    public Sitter getSitterById(Long id) {
        return sitterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + id));
    }

    /**
     * Updates an existing Sitter's information.
     *
     * @param id Sitter ID to update.
     * @param updatedSitter Updated Sitter data.
     * @return Updated Sitter.
     */
    public Sitter updateSitter(Long id, Sitter updatedSitter) {
        // Check if sitter exists
        Sitter existingSitter = sitterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + id));

        // Update fields only if non-null and valid
        if (updatedSitter.getFirstName() != null && !updatedSitter.getFirstName().trim().isEmpty()) {
            existingSitter.setFirstName(updatedSitter.getFirstName().trim());
        }

        if (updatedSitter.getLastName() != null && !updatedSitter.getLastName().trim().isEmpty()) {
            existingSitter.setLastName(updatedSitter.getLastName().trim());
        }

        if (updatedSitter.getEmailId() != null && !updatedSitter.getEmailId().trim().isEmpty()) {
            // Prevent duplicate emails during update
            Sitter emailOwner = sitterRepository.findByEmailId(updatedSitter.getEmailId().trim());
            if (emailOwner != null && !emailOwner.getId().equals(existingSitter.getId())) {
                throw new RuntimeException("Email already exists: " + updatedSitter.getEmailId());
            }
            existingSitter.setEmailId(updatedSitter.getEmailId().trim());
        }

        if (updatedSitter.getPassword() != null && !updatedSitter.getPassword().trim().isEmpty()) {
            existingSitter.setPassword(updatedSitter.getPassword().trim());
        }

        if (updatedSitter.getPhoneNumber() != null) {
            existingSitter.setPhoneNumber(updatedSitter.getPhoneNumber());
        }

        if (updatedSitter.getAddress() != null && !updatedSitter.getAddress().trim().isEmpty()) {
            existingSitter.setAddress(updatedSitter.getAddress().trim());
        }

        if (updatedSitter.getBio() != null && !updatedSitter.getBio().trim().isEmpty()) {
            existingSitter.setBio(updatedSitter.getBio().trim());
        }

        return sitterRepository.save(existingSitter);
    }

    /**
     * Deletes a Sitter by ID.
     *
     * @param id Sitter ID to delete.
     */
    public void deleteSitter(Long id) {
        if (!sitterRepository.existsById(id)) {
            throw new RuntimeException("Sitter not found with ID: " + id);
        }

        sitterRepository.deleteById(id);
    }
}
