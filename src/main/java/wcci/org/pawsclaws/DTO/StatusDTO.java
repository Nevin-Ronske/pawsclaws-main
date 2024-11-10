package wcci.org.pawsclaws.DTO; // Package declaration for Data Transfer Objects related to Paws and Claws application

import wcci.org.pawsclaws.Enums.PetType; // Importing the PetType enumeration

// StatusDTO class extends ErrorDataDTO to represent the status of a pet
public class StatusDTO extends ErrorDataDTO {
    // Name associated with the status
    private String name;

    // Type of pet (e.g., cat, dog, robotic)
    private PetType petType;

    // Current status of the pet (e.g., happy, sad)
    private String status;

    // Value associated with the status (could be a numeric or descriptive value)
    private String value;

    // Default constructor that calls the superclass constructor
    public StatusDTO() {
        super();
    }

    // Constructor to initialize the StatusDTO with specific values
    public StatusDTO(String name, PetType petType, String status, String value) {
        this.name = name;
        this.petType = petType;
        this.status = status;
        this.value = value;
    }

    // Getter for the name
    public String getName() {
        return name;
    }

    // Setter for the name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the pet type
    public PetType getPetType() {
        return petType;
    }

    // Setter for the pet type
    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    // Getter for the current status
    public String getStatus() {
        return status;
    }

    // Setter for the current status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for the value associated with the status
    public String getValue() {
        return value;
    }

    // Setter for the value associated with the status
    public void setValue(String value) {
        this.value = value;
    }
}
