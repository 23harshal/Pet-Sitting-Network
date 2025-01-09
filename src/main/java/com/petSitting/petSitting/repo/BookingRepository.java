package com.petSitting.petSitting.repo;

import com.petSitting.petSitting.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Long , Booking> {
}
