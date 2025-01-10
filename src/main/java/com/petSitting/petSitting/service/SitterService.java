package com.petSitting.petSitting.service;

import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.repo.SitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SitterService {

    @Autowired
    private SitterRepository sitterRepository;

    public Sitter createSitter(Sitter sitter) {
        try {
            Sitter exsitedSitter = sitterRepository.findByEmailId(sitter.getEmailId());
            if (exsitedSitter != null) {
                throw new Exception("Email already exists");
            }
            sitterRepository.save(sitter);
            return sitter;
        } catch (Exception e) {
            throw new RuntimeException("problem while creating a sitter account");

        }
    }

    public Sitter getSitterById(Long id) {
        return sitterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sitter not found"));
    }

    public Sitter updateSitter(Sitter updatedSitter) {
        if (!sitterRepository.existsById(updatedSitter.getId())) {
            throw new RuntimeException("Sitter not found");
        }
        Sitter existingSitter = getSitterById(updatedSitter.getId());

        if (updatedSitter.getFirstName() != null && !updatedSitter.getFirstName().trim().isEmpty()) {
            existingSitter.setFirstName(updatedSitter.getFirstName().trim());
        }

        if (updatedSitter.getLastName() != null && !updatedSitter.getLastName().trim().isEmpty()) {
            existingSitter.setLastName(updatedSitter.getLastName().trim());
        }

        if (updatedSitter.getEmailId() != null && !updatedSitter.getEmailId().trim().isEmpty()) {
            existingSitter.setEmailId(updatedSitter.getEmailId().trim());
        }

        if (updatedSitter.getPassword() != null && !updatedSitter.getPassword().trim().isEmpty()) {
            existingSitter.setPassword(updatedSitter.getPassword().trim());
        }

        if (updatedSitter.getPhoneNumber() != null) {
            existingSitter.setPhoneNumber(updatedSitter.getPhoneNumber());
        }
        return sitterRepository.save(existingSitter);
    }

    public String deleteSitter(Long id) {
        try {
            if (!sitterRepository.existsById(id)) {
                throw new RuntimeException("Sitter not found");
            }
            sitterRepository.deleteById(id);
            return "sitter account deleted successfully...";
        } catch (Exception e) {
            throw new RuntimeException("problem while deleting a sitter account");
        }
    }


}
