package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot , Long> {
}
