package wcci.org.pawsclaws.DTO; // Package declaration for Data Transfer Objects related to Paws and Claws application

import wcci.org.pawsclaws.Enums.PetType; // Importing the PetType enumeration

// PetDTO class extends ErrorDataDTO to represent a pet's data transfer object
public class PetDTO extends ErrorDataDTO {
    // Unique identifier for the pet
    private long id;

    // Type of pet (e.g., cat, dog, robotic)
    private PetType PetType;

    // Name of the pet
    private String name;

    // Age of the pet
    private Integer age;

    // Health status of the pet (on a scale)
    private int health;

    // Happiness level of the pet (on a scale)
    private int happiness;

    // Cause of death if applicable
    private String deathBy;

    // Hunger level of the pet (on a scale)
    private int hunger;

    // Thirst level of the pet (on a scale)
    private int thirst;

    // Current status of the pet (e.g., alive, dead)
    private String status;

    // Oil level for robotic pets (on a scale)
    private int oil;

    // Battery level for robotic pets (on a scale)
    private int battery;

    // URL or path to the pet's image
    private String image;

    // Getter for the pet's unique ID
    public long getId() {
        return id;
    }

    // Setter for the pet's unique ID
    public void setId(long id) {
        this.id = id;
    }

    // Getter for the pet type
    public PetType getPetType() {
        return PetType;
    }

    // Setter for the pet type
    public void setPetType(PetType petType) {
        PetType = petType;
    }

    // Getter for the pet's name
    public String getName() {
        return name;
    }

    // Setter for the pet's name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the pet's age
    public Integer getAge() {
        return age;
    }

    // Setter for the pet's age
    public void setAge(Integer age) {
        this.age = age;
    }

    // Getter for the pet's health
    public int getHealth() {
        return health;
    }

    // Setter for the pet's health
    public void setHealth(int health) {
        this.health = health;
    }

    // Getter for the pet's happiness
    public int getHappiness() {
        return happiness;
    }

    // Setter for the pet's happiness
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    // Getter for the cause of death
    public String getDeathBy() {
        return deathBy;
    }

    // Setter for the cause of death
    public void setDeathBy(String deathBy) {
        this.deathBy = deathBy;
    }

    // Getter for the pet's hunger level
    public int getHunger() {
        return hunger;
    }

    // Setter for the pet's hunger level
    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    // Getter for the pet's thirst level
    public int getThirst() {
        return thirst;
    }

    // Setter for the pet's thirst level
    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    // Getter for the oil level (for robotic pets)
    public int getOil() {
        return oil;
    }

    // Setter for the oil level (for robotic pets)
    public void setOil(int oil) {
        this.oil = oil;
    }

    // Getter for the battery level (for robotic pets)
    public int getBattery() {
        return battery;
    }

    // Setter for the battery level (for robotic pets)
    public void setBattery(int battery) {
        this.battery = battery;
    }

    // Getter for the current status of the pet
    public String getStatus() {
        return status;
    }

    // Setter for the current status of the pet
    public void setStatus(String status) {
        this.status = status;
    }

    // Method to check if the pet is a robotic pet
    public boolean isRobot() {
        return (this.getPetType() == PetType.RoboticCat || this.getPetType() == PetType.RoboticDog);
    }

    // Getter for the pet's image URL or path
    public String getImage() {
        return image;
    }

    // Setter for the pet's image URL or path
    public void setImage(String image) {
        this.image = image;
    }
}
