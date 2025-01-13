package com.petSitting.petSitting.models;

import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class WorkingSchedule {
    private LocalTime startTime;
    private LocalTime endTime;

    @ElementCollection
    private List<DayOfWeek> availableDays = new ArrayList<>();

    // Getters and Setters
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<DayOfWeek> availableDays) {
        this.availableDays = availableDays;
    }
}
