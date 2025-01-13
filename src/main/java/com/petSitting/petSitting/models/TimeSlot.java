package com.petSitting.petSitting.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "sitter_id")
    private Sitter sitter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User bookedBy;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
