package com.petSitting.petSitting.service;

import com.petSitting.petSitting.dto.ServiceRequest;
import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.models.WorkingSchedule;
import com.petSitting.petSitting.repo.SitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Sitter addMultipleServices(Long sitterId, List<ServiceRequest> servicesToAdd) {
        Sitter existingSitter = sitterRepository.findById(sitterId)
                .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + sitterId));

        Map<String, BigDecimal> services = existingSitter.getServices();

        if (services == null) {
            services = new HashMap<>();
        }

        for (ServiceRequest service : servicesToAdd) {
            services.put(service.getServiceName(), service.getServicePrice());
        }

        existingSitter.setServices(services);
        return sitterRepository.save(existingSitter);
    }

    public Sitter removeMultipleServices(Long sitterId, List<String> servicesToRemove) {
        Sitter existingSitter = sitterRepository.findById(sitterId)
                .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + sitterId));

        Map<String, BigDecimal> services = existingSitter.getServices();

        if (services == null) {
            throw new RuntimeException("No services available to remove");
        }

        for (String serviceName : servicesToRemove) {
            if (services.containsKey(serviceName)) {
                services.remove(serviceName);
            } else {
                throw new RuntimeException("Service not found: " + serviceName);
            }
        }

        existingSitter.setServices(services);
        return sitterRepository.save(existingSitter);
    }


    // Update a service
    public Sitter updateMultipleServices(Long sitterId, List<ServiceRequest> servicesToUpdate) {
        Sitter existingSitter = sitterRepository.findById(sitterId)
                .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + sitterId));

        Map<String, BigDecimal> services = existingSitter.getServices();

        if (services == null) {
            services = new HashMap<>();
        }

        for (ServiceRequest service : servicesToUpdate) {
            if (services.containsKey(service.getServiceName())) {
                // Update the service price
                services.put(service.getServiceName(), service.getServicePrice());
            } else {
                throw new RuntimeException("Service not found: " + service.getServiceName());
            }
        }

        existingSitter.setServices(services);
        return sitterRepository.save(existingSitter);
    }
    public Sitter updateWorkingSchedule(Long sitterId, WorkingSchedule schedule) {
        Sitter sitter = sitterRepository.findById(sitterId)
                .orElseThrow(() -> new RuntimeException("Sitter not found"));

        sitter.setWorkingSchedule(schedule);
        return sitterRepository.save(sitter);
    }



    public void deleteSitter(Long id) {
        if (!sitterRepository.existsById(id)) {
            throw new RuntimeException("Sitter not found with ID: " + id);
        }

        sitterRepository.deleteById(id);
    }
}
