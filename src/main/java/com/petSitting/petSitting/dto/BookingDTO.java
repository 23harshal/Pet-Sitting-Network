package com.petSitting.petSitting.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingDTO {
    private Long bookingId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked;

    public BookingDTO() {
    }

    public BookingDTO(Long bookingId, LocalDate date, LocalTime startTime, LocalTime endTime, boolean isBooked) {
        this.bookingId = bookingId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
