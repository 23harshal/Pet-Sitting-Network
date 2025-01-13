package com.petSitting.petSitting.controllers;


import com.petSitting.petSitting.models.Sitter;
import com.petSitting.petSitting.models.TimeSlot;
import com.petSitting.petSitting.models.WorkingSchedule;
import com.petSitting.petSitting.repo.SitterRepository;
import com.petSitting.petSitting.repo.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeSlotService {

    @Autowired
    private SitterRepository sitterRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getAvailableTimeSlot(Long sitterId){
        try {
            Sitter sitter = sitterRepository.findById(sitterId)
                    .orElseThrow(() -> new RuntimeException("Sitter not found"));
            WorkingSchedule workingSchedule = sitter.getWorkingSchedule();
            if(workingSchedule == null){
                throw new RuntimeException("Working schedule not found for sitter");
            }
            List<TimeSlot> generatedSlots = new ArrayList<>();
            LocalDate today = LocalDate.now();

            for(int i = 0 ; i<7 ;i++){
                LocalDate currentDate = today.plusDays(i);

                if(workingSchedule.getAvailableDays().contains(currentDate.getDayOfWeek())){
                    LocalTime startTime = workingSchedule.getStartTime();
                    LocalTime endTime = workingSchedule.getEndTime();

                    while(startTime.isBefore(endTime)){
                        TimeSlot slot = new TimeSlot();
                        slot.setDate(currentDate);
                        slot.setStartTime(startTime);
                        slot.setEndTime(startTime.plusHours(1));
                        slot.setSitter(sitter);
                        slot.setBooked(false);

                        generatedSlots.add(slot);
                        startTime = startTime.plusHours(1);
                    }
                }
            }
            return generatedSlots.stream()
                    .filter(slot -> !slot.isBooked())
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            throw  new RuntimeException("eror while getting avalilable time slot");
        }
    }



}
