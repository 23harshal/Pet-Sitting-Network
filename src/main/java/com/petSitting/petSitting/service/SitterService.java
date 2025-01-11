package com.petSitting.petSitting.service;

import com.petSitting.petSitting.dto.ServiceRequest;
import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.repo.SitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
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

    public Sitter addService(Long sitterId , ServiceRequest serviceRequest){
        Sitter existingSItter = sitterRepository.findById(sitterId)
               .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + sitterId));
        Map<String , BigDecimal> services  = existingSItter.getServices();
        if(services == null){
            services = new HashMap<>();
        }
        services.put(serviceRequest.getServiceName() , serviceRequest.getServicePrice());
        existingSItter.setServices(services);
        return sitterRepository.save(existingSItter);
    }
    public Sitter removeService(Long sitterId, String serviceName){
        Sitter existingSItter = sitterRepository.findById(sitterId)
               .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + sitterId));
        Map<String, BigDecimal> services = existingSItter.getServices();
        if (services == null || !services.containsKey(serviceName)) {
            throw new RuntimeException("Service not found: " + serviceName);
        }
        services.remove(serviceName);
        existingSItter.setServices(services);
        return sitterRepository.save(existingSItter);
    }

    // Update a service
    public Sitter updateService(Long sitterId, String oldServiceName, ServiceRequest updatedService) {
        Sitter existingSitter = sitterRepository.findById(sitterId)
                .orElseThrow(() -> new RuntimeException("Sitter not found with ID: " + sitterId));

        Map<String, BigDecimal> services = existingSitter.getServices();

        if (services == null || !services.containsKey(oldServiceName)) {
            throw new RuntimeException("Service not found: " + oldServiceName);
        }

        // Update service name
        if (!oldServiceName.equals(updatedService.getServiceName())) {
            BigDecimal oldPrice = services.remove(oldServiceName);
            services.put(updatedService.getServiceName(), updatedService.getServicePrice() != null ? updatedService.getServicePrice() : oldPrice);
        } else {
            // Update service price only if a new price is provided
            services.put(oldServiceName, updatedService.getServicePrice() != null ? updatedService.getServicePrice() : services.get(oldServiceName));
        }

        existingSitter.setServices(services);
        return sitterRepository.save(existingSitter);
    }


    public void deleteSitter(Long id) {
        if (!sitterRepository.existsById(id)) {
            throw new RuntimeException("Sitter not found with ID: " + id);
        }

        sitterRepository.deleteById(id);
    }
}
