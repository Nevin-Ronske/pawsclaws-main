package wcci.org.pawsclaws.DTO;

public class EditPetDTO extends ErrorDataDTO {
    private long id; // Unique identifier for the pet
    private String name; // Name of the pet
    private Integer age; // Age of the pet

    // Constructor that initializes from a PetDTO object
    public EditPetDTO(PetDTO pet) {
        this.id = pet.getId(); // Set ID from the PetDTO
        this.name = pet.getName(); // Set name from the PetDTO
        this.age = pet.getAge(); // Set age from the PetDTO
    }

    // Default constructor
    public EditPetDTO() {
    }

    // Getter for pet ID
    public long getId() {
        return id;
    }

    // Setter for pet ID
    public void setId(long id) {
        this.id = id;
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
}
