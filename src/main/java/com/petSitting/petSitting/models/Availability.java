package com.petSitting.petSitting.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sitter_id", nullable = false)
    private Sitter sitter;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Boolean isAvailable;

    public Availability() {
    }

    public Availability(Sitter sitter, LocalDateTime endTime, LocalDateTime startTime, Boolean isAvailable) {
        this.sitter = sitter;
        this.endTime = endTime;
        this.startTime = startTime;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
