package com.petSitting.petSitting.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(nullable = false , unique = true)
    private String emailId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true , unique = true, length = 10)
    private Long phoneNumber;

    @OneToMany(mappedBy = "bookedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    public User() {
    }

    public User(Long id, String firstName, String lastName, String emailId, String password, Long phoneNumber, List<TimeSlot> bookings, List<Pet> pets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bookings = bookings;
        this.pets = pets;
    }


    public List<TimeSlot> getBookings() {
        return bookings;
    }

    public void setBookings(List<TimeSlot> bookings) {
        this.bookings = bookings;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}