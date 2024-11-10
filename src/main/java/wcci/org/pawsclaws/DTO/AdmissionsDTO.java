package wcci.org.pawsclaws.DTO;

import wcci.org.pawsclaws.Enums.PetType;

public class AdmissionsDTO extends ErrorDataDTO {
    private String name; // Name of the pet
    private Integer age; // Age of the pet
    private PetType petType; // Type of the pet (e.g., Dog, Cat, etc.)

    // Constructor to initialize the AdmissionsDTO object with name, age, and
    // petType
    public AdmissionsDTO(String name, Integer age, PetType petType) {
        this.name = name; // Set the name
        this.age = age; // Set the age
        this.petType = petType; // Set the pet type
    }

    // Getter for pet name
    public String getName() {
        return name;
    }

    // Setter for pet name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for pet age
    public Integer getAge() {
        return age;
    }

    // Setter for pet age
    public void setAge(Integer age) {
        this.age = age;
    }

    // Getter for pet type
    public PetType getPetType() {
        return petType;
    }

    // Setter for pet type
    public void setPetType(PetType petType) {
        this.petType = petType;
    }
}
