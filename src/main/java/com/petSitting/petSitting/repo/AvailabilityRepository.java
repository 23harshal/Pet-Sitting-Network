package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository< Availability , Long> {
}
