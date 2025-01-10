package com.petSitting.petSitting.models;

import com.petSitting.petSitting.utils.MapToJsonConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
public class Sitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String emailId;
    @Column(nullable = false , length = 10)
    private Long phoneNumber;

    @Column(nullable = false)
    private String password;

    private String address;
    private String bio;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String , BigDecimal> services;

    public Sitter() {
    }

    public Sitter(String firstName, String emailId, String password, Map<String, BigDecimal> services, String bio, String address, String lastName, Long phoneNumber) {
        this.firstName = firstName;
        this.emailId = emailId;
        this.password = password;
        this.services = services;
        this.bio = bio;
        this.address = address;
        this.lastName = lastName;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, BigDecimal> getServices() {
        return services;
    }

    public void setServices(Map<String, BigDecimal> services) {
        this.services = services;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Sitter{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", bio='" + bio + '\'' +
                ", services=" + services +
                '}';
    }
}
