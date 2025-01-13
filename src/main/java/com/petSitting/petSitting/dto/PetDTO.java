package com.petSitting.petSitting.dto;

public class PetDTO {
    private Long id;
    private String name;
    private UserDTO owner;

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public PetDTO() {
    }

    public PetDTO(Long id, String name, UserDTO owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public PetDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
