package com.petSitting.petSitting.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<PetDTO> pets;
    private List<BookingDTO> bookings;

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email, List<PetDTO> pets, List<BookingDTO> bookings) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pets = pets;
        this.bookings = bookings;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PetDTO> getPets() {
        return pets;
    }

    public void setPets(List<PetDTO> pets) {
        this.pets = pets;
    }

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDto = (UserDTO) o;
        return Objects.equals(id, userDto.id) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(email, userDto.email) && Objects.equals(pets, userDto.pets) && Objects.equals(bookings, userDto.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, pets, bookings);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", pets=" + pets +
                ", bookings=" + bookings +
                '}';
    }
}
