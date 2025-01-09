package com.petSitting.petSitting.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String petName;
    @Column(nullable = false)
    private String type;
    private String breed;
    private String age;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id ", nullable = false)
    private User owner;

    public Pet() {
    }

    public Pet(String type, String petName, String breed, String age, String description, User owner) {
        this.type = type;
        this.petName = petName;
        this.breed = breed;
        this.age = age;
        this.description = description;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }



    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petName='" + petName + '\'' +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                ", age='" + age + '\'' +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                '}';
    }
}
